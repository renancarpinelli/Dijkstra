package renan.victor.com.br.sliding.dao;

/**
 * Created by RananHome on 08/05/2017.
 */

public class GraphInserts {

    public static String[] GraphInserts = new String[]{
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('POA', 'PORTO ALEGRE', '-30.036777','-51.217484');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('FLO', 'FLORIANOPOLIS', '-27.601649','-48.541016');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('BLU', 'BLUMENAU', '-26.917774','-49.071388');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('CUR', 'CURITIBA', '-25.429376','-49.267206');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('LON', 'LONDRINA', '-23.305212','-51.169424');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('SAO', 'SAO PAULO', '-23.550412','-46.633792');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('SJC', 'SJ DOS CAMPOS', '-23.233608','-45.894710');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('RJO', 'RIO DE JANEIRO', '-22.908381','-43.172132');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('BAU', 'BAURU', '-22.334187','-49.045083');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('RBP', 'RIBEIRAO PRETO', '-21.186682','-47.821626');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('CMP', 'CAMPINAS', '-22.928871','-47.056002');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('BHO', 'BELO HORIZONTE', '-19.921881','-43.939445');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('CPG', 'CAMPO GRANDE', '-20.484484','-54.622834');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('BSB', 'BRASILIA', '-15.798559','-47.884488');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('SLV', 'SALVADOR', '-12.976233','-38.502651');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('REC', 'RECIFE', '-8.062085','-34.881664');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('NTL', 'NATAL', '-5.782727','-35.200225');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('MAN', 'MANAUS', '-3.132975','-60.023097');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('BEL', 'BELEM', '-1.465951','-48.479231');" ,
            "INSERT INTO pop (_id, name, latitude, longitude) VALUES ('CUI', 'CUIABA', '-15.605160','-56.099074');" ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('POA','FLO',1,6,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('POA','BLU',1,7,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('FLO','BLU',1,1,3); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('FLO','CUR',1,2,5); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('FLO','RJO',1,12,10); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BLU','CUR',1,2,5); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('CUR','LON',1,6,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('CUR','SAO',1,5,10); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('LON','SAO',1,7,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('LON','BAU',1,3,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('SAO','RJO',1,5,15); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('SAO','CMP',1,1,7); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('SAO','SJC',1,2,16); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('SJC','CMP',1,2,10); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('RJO','SJC',1,3,10); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('RJO','BHO',1,7,6); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('RJO','SLV',1,20,6); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BHO','SJC',1,7,8); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BHO','BSB',1,9,6); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('CMP','BAU',1,3,6); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('CMP','RBP',1,2,4); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('RBP','BSB',1,8,4); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BAU','CPG',1,10,3); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('CPG','CUI',1,8,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('CUI','MAN',1,20,3); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('MAN','BEL',1,18,2); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BEL','NTL',1,21,3); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BSB','MAN',1,22,6); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('BSB','NTL',1,22,7); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('NTL','REC',1,4,3); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('REC','SLV',1,8,5); "  ,
            "INSERT INTO enlance (_id_pop_a, _id_pop_b, metrica1, metrica2, metrica3) VALUES ('SLV','NTL',1,15,4); "  ,
    };


}
