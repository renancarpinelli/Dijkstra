package renan.victor.com.br.sliding;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelSlideListener;
import com.sothree.slidinguppanel.SlidingUpPanelLayout.PanelState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renan.victor.com.br.sliding.controller.GraphController;
import renan.victor.com.br.sliding.model.Enlance;
import renan.victor.com.br.sliding.model.MetricaType;
import renan.victor.com.br.sliding.model.Pop;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "DemoActivity";

    private Boolean isReady = false;
    private GoogleMap mMap;
    private GraphController graphController;
    private List<Marker> markers;
    private List<Polyline> polylines;
    private List<Pop> popList;
    private List<Enlance> enlances;
    private ListView list;
    private Pop pop1, pop2;
    private Marker marker;
    private MetricaType metrica = MetricaType.METRICA1;
    private String origem, destino;
    private Spinner spinnerOrigem, spinnerDestino, spinnerMetrica;
    private SupportMapFragment mMapFragment;
    private SlidingUpPanelLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Inicializando GraphController
        graphController = new GraphController(getApplicationContext());

        // Sliding Layout
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mLayout.addPanelSlideListener(new PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (slideOffset > 0.7475791) {
                    ((LinearLayout) findViewById(R.id.containerTop)).setVisibility(View.VISIBLE);
                    ((LinearLayout) findViewById(R.id.containerDown)).setVisibility(View.GONE);
                } else if (slideOffset < 0.7475791) {
                    ((LinearLayout) findViewById(R.id.containerTop)).setVisibility(View.GONE);
                    ((LinearLayout) findViewById(R.id.containerDown)).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPanelStateChanged(View panel, PanelState previousState, PanelState newState) {
            }
        });
        mLayout.setFadeOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(PanelState.COLLAPSED);
            }
        });


        // Populando Spinners
        ArrayAdapter<Pop> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, graphController.getPops());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Spinner origem
        spinnerOrigem = (Spinner) findViewById(R.id.origem);
        spinnerOrigem.setAdapter(adapter);
        spinnerOrigem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                origem = ((Pop) spinnerOrigem.getSelectedItem()).getId();
                ((TextView) findViewById(R.id.origem2)).setText(origem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner destino
        spinnerDestino = (Spinner) findViewById(R.id.destino);
        spinnerDestino.setAdapter(adapter);
        spinnerDestino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destino = ((Pop) spinnerDestino.getSelectedItem()).getId();
                ((TextView) findViewById(R.id.destino2)).setText(destino);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner metrica
        spinnerMetrica = (Spinner) findViewById(R.id.metica);
        spinnerMetrica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        metrica = MetricaType.METRICA1;
                        ((TextView) findViewById(R.id.txtMetrica)).setText(getResources().getString(R.string.metria1));
                        break;
                    case 1:
                        metrica = MetricaType.METRICA2;
                        ((TextView) findViewById(R.id.txtMetrica)).setText(getResources().getString(R.string.metria2));
                        break;
                    case 2:
                        metrica = MetricaType.METRICA3;
                        ((TextView) findViewById(R.id.txtMetrica)).setText(getResources().getString(R.string.metria3));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setupMap();

    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng latLng = new LatLng(-15.798559, -47.884488);
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(4).build();
        CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(update, 1000, new GoogleMap.CancelableCallback() {
            @Override
            public void onFinish() {

            }

            @Override
            public void onCancel() {

            }
        });

        createGraph(map);

        if (isReady) {
            createMinPath(map);
            isReady = false;
        }
    }

    private void createGraph(GoogleMap map) {
        map.clear();

        if (pop1 != null && pop2 == null) {
            enlances = graphController.createGraph(metrica, pop1.getId(), pop1.getId());
            ((TextView) findViewById(R.id.txtFalha)).setText("Falha no Pop de " + pop1.getName() + " (" + pop1.getId() + ").");
            ((android.support.v7.widget.CardView) findViewById(R.id.cardFalha)).setVisibility(View.VISIBLE);
            addMarkerDisable(map, pop1);
        } else if (pop2 != null) {
            enlances = graphController.createGraph(metrica, pop1.getId(), pop2.getId());
            addPolylineRed(map, pop1.getLatLng(), pop2.getLatLng());
            ((TextView) findViewById(R.id.txtFalha)).setText("Falha no enlace entre " + pop1.getName() + " (" + pop1.getId() + ") e " + pop2.getName() + " (" + pop2.getId() + ")");
            ((android.support.v7.widget.CardView) findViewById(R.id.cardFalha)).setVisibility(View.VISIBLE);
        } else {
            ((android.support.v7.widget.CardView) findViewById(R.id.cardFalha)).setVisibility(View.GONE);
            enlances = graphController.createGraph(metrica, "", "");
        }

        for (Enlance e : enlances) {
            addMarker(map, e.getPopOrigem());
            addMarker(map, e.getPopDestino());
            addPolylineGray(map, e.getPopOrigem().getLatLng(), e.getPopDestino().getLatLng());
        }
    }

    public void menorCaminho(View view) {
        if (origem.equals(destino)) {
            Toast.makeText(getApplicationContext(), "Origem deve ser diferente de destino", Toast.LENGTH_SHORT).show();
        } else if ((pop1 != null && pop2 == null) && (origem.equals(pop1.getId()) || destino.equals(pop1.getId()))) {
            Toast.makeText(getApplicationContext(), " O Pop de " + pop1.getName() + " (" + pop1.getId() + ") está com falha", Toast.LENGTH_SHORT).show();
        } else {
            isReady = true;
            mMapFragment.getMapAsync(this);
        }

    }

    private void setupMap() {
        mMapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
        mMapFragment.getMapAsync(this);
    }

    private void createMinPath(GoogleMap map) {
        popList = graphController.runDijkstra(origem, destino);
        ((TextView) findViewById(R.id.minPath)).setText(graphController.getMinPath(destino));
        for (int i = 0; i < popList.size() - 1; i++) {
            addPolylineBlue(map, popList.get(i).getLatLng(), popList.get(i + 1).getLatLng());
        }
    }

    private void addPolylineGray(GoogleMap map, LatLng latLng1, LatLng latLng2) {
        // Desenha uma linha entre 2 pontos
        PolylineOptions line = new PolylineOptions();
        line.add(latLng1);
        line.add(latLng2);
        line.color(Color.GRAY);
        line.width(5);
        Polyline polyline = map.addPolyline(line);
        polyline.setGeodesic(true);
    }

    private void addPolylineBlue(GoogleMap map, LatLng latLng1, LatLng latLng2) {
        // Desenha uma linha entre 2 pontos
        PolylineOptions line = new PolylineOptions();
        line.add(latLng1);
        line.add(latLng2);
        line.color(Color.parseColor("#00ffff"));
        line.width(10);
        Polyline polyline = map.addPolyline(line);
        polyline.setClickable(true);
    }

    private void addPolylineRed(GoogleMap map, LatLng latLng1, LatLng latLng2) {
        // Desenha uma linha entre 2 pontos
        PolylineOptions line = new PolylineOptions();
        line.add(latLng1);
        line.add(latLng2);
        line.color(Color.RED);
        line.width(5);
        Polyline polyline = map.addPolyline(line);
        polyline.setClickable(true);
    }

    public void addMarker(GoogleMap map, Pop pop) {

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pop.getLatLng()).title(pop.getId()).snippet(pop.getName());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.popenable));

        Marker marker = map.addMarker(markerOptions);

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // View com o conteúdo
                LinearLayout linear = new LinearLayout(getBaseContext());
                linear.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                linear.setOrientation(LinearLayout.VERTICAL);

                TextView tTitle = new TextView(getBaseContext());
                tTitle.setText(marker.getTitle() + " - " + marker.getSnippet());
                tTitle.setTextColor(Color.BLACK);
                linear.addView(tTitle);

                if (((pop1 != null) && pop1.getId().equals(marker.getTitle())) || ((pop2 != null) && pop2.getId().equals(marker.getTitle()))) {
                    TextView tSnippet = new TextView(getBaseContext());
                    tSnippet.setText("HABILITAR");
                    tSnippet.setTextColor(Color.BLUE);
                    linear.addView(tSnippet);
                } else {
                    TextView tSnippet = new TextView(getBaseContext());
                    tSnippet.setText("DESABILITAR");
                    tSnippet.setTextColor(Color.RED);
                    linear.addView(tSnippet);
                }


                return linear;
            }
        });


        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (((pop1 != null) && pop1.getId().equals(marker.getTitle())) || ((pop2 != null) && pop2.getId().equals(marker.getTitle()))) {
                    Toast.makeText(getApplicationContext(), "Mantenha precionado para HABILITAR", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Mantenha precionado para DESABILITAR", Toast.LENGTH_SHORT).show();
                }
            }
        });


        map.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
            @Override
            public void onInfoWindowLongClick(Marker marker) {
                removeMarker(marker.getTitle());
            }
        });
    }

    public void addMarkerDisable(GoogleMap map, Pop pop) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pop.getLatLng()).title(pop.getId()).snippet(pop.getName());
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.popdisable));

        Marker marker = map.addMarker(markerOptions);

        map.setOnInfoWindowLongClickListener(new GoogleMap.OnInfoWindowLongClickListener() {
            @Override
            public void onInfoWindowLongClick(Marker marker) {
                removeMarker(marker.getTitle());
            }
        });
    }

    private void removeMarker(String id) {
        if (pop1 == null) {
            pop1 = graphController.getPop(id);
            Toast.makeText(getApplicationContext(), "Falha no Pop de " + pop1.getName() + " (" + pop1.getId() + ").", Toast.LENGTH_LONG).show();
        } else {
            if (pop1.getId().equals(id)) {
                if (pop2 != null) {
                    pop1 = pop2;
                    pop2 = null;
                    Toast.makeText(getApplicationContext(), "Falha no Pop de " + pop1.getName() + " (" + pop1.getId() + ").", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Pop de" + pop1.getName() + " (" + pop1.getId() + ") habilitado", Toast.LENGTH_LONG).show();
                    pop1 = null;
                }
            } else {
                if (pop2 != null && pop2.getId().equals(id)) {
                    Toast.makeText(getApplicationContext(), "Falha no Pop de " + pop1.getName() + " (" + pop1.getId() + ").", Toast.LENGTH_LONG).show();
                    pop2 = null;
                } else {
                    pop2 = graphController.getPop(id);
                    Toast.makeText(getApplicationContext(), "Falha no enlace entre " + pop1.getName() + " (" + pop1.getId() + ") e " + pop2.getName() + " (" + pop2.getId() + ")", Toast.LENGTH_LONG).show();
                }
            }
        }
        setupMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

