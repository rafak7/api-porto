//package br.com.fiap.portoapi.model;
//
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import br.com.fiap.portoapi.model.repository.ConnectionFactory;
//
//public class PortoDao<var> {
//
//        public List<Porto> FindAll(){
//           
//            var lista = new ArrayList<Porto>();
// 
//           try {
//            var conexao = ConnectionFactory.getConnection();
//            var comando = conexao.prepareStatement("SELECT veiculos.*, clientes.nome, clientes.email, clientes.telefone FROM veiculos INNER JOIN clientes ON veiculos.cliente_id=clientes.id");
//            var resultado = comando.executeQuery();
// 
//            while(resultado.next()){
//                lista.add (new Porto(
//                    resultado.getLong("id"),
//                    resultado.getString("titulo"),
//                    resultado.getString("sinopse"),
//                    resultado.getString("poster")
//                    ));
//            }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//           
//            return lista ;
//    }
// 
//       
//        public Porto findById(Long id) {
//            Porto porto = null;
//               try {
//                var conexao = ConnectionFactory.getConnection();
//                var comando = conexao.prepareStatement("SELECT Desenhos.*, Desenhos.titulo, Desenhos.sinopse, clientes.telefone FROM veiculos INNER JOIN clientes ON veiculos.cliente_id=clientes.id");
//                var resultado = comando.executeQuery();
// 
//                while(resultado.next()){
//                    porto = (new porto(
//                        resultado.getLong("id"),
//                        resultado.getString("titulo"),
//                        resultado.getString("sinopse"),
//                        resultado.getString("poster")
//                        ));
//                }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//               
//               return porto;
//               
//        }
//       
//         public static void apagar(Long id) throws SQLException{
//                var conexao = DriverManager.getConnection(URL, USER, PASS);
//                var comando = conexao.prepareStatement("DELETE FROM veiculos WHERE id=?");
//                comando.setLong(1, id);
//                comando.executeUpdate();
//                conexao.close();
//            }
//}
//	
//	
//
