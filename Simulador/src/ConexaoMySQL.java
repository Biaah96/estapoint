import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConexaoMySQL {

  private static String driverName = "com.mysql.jdbc.Driver";
  private static String serverName = "201.30.92.49:3306";
  private static String db ="estacio";
  private static String url = "jdbc:mysql://" + serverName + "/" + db;
  private static String username = "estacio";
  private static String password = "q1w2e3r4t5T%";
  private static Connection conn = null;
  private static Statement st = null;

  public ConexaoMySQL() {
  }

  public static java.sql.Connection getConexaoMySQL() {
      Connection connection = null;
      try {
          Class.forName(driverName);
          connection = DriverManager.getConnection(url, username, password);
          if (connection != null) {
              System.out.println("STATUS--->Conectado com sucesso!");
          } else {
              System.out.println("STATUS--->Não foi possivel realizar conexão");
          }
          return connection;
      } catch (ClassNotFoundException e) {  //Driver não encontrado
          System.out.println("O driver especificado nao foi encontrado.");
          return null;
      } catch (SQLException e) {
          System.out.println("Nao foi possivel conectar ao Banco de Dados.");
          return null;
      }


  }
  public static boolean fecharConexao() {
      try {
          ConexaoMySQL.getConexaoMySQL().close();
          return true;
      } catch (SQLException e) {
          return false;
      }
  }
  public static java.sql.Connection reiniciarConexao() {
      fecharConexao();
      return ConexaoMySQL.getConexaoMySQL();

  }

  public static boolean vagaOcupada(int v){
	  boolean result = false;
	    try {
			ResultSet rs = st.executeQuery("select * from vagas where vaga="+v);
			int c=0;
			while (rs.next()){
				c++;
			}
			if (c>0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return result;	  
  }
  
  public static void estacionar(Carro carro, int vaga){
	  try {
		st.execute("insert into vagas values(null,'"+carro.getPlaca()+"','mauricio@pontuschka.com',"+vaga+",1)");
	  } catch (SQLException e) {
		e.printStackTrace();
	  }
  }
  
  public static void sairDaVaga(int vaga){
	  try {
			st.execute("delete from vagas where vaga="+vaga);
	  } catch (SQLException e) {
			e.printStackTrace();
	  }
  }
  
  public static void open(){
	    conn = ConexaoMySQL.getConexaoMySQL();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
  
  public static void close(){
	    try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
  }
  public static void outracoisa(Carro carro, int vaga){
	  Connection conn = ConexaoMySQL.getConexaoMySQL();
		try {
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("select * from vagas where vaga=19");
			while (rs.next()){
				System.out.println(rs.getString("placa")+" "+rs.getString("email"));
			}
			conn.commit();
			
			st.execute("insert into vagas values(null,'"+carro.getPlaca()+"','mauricio@pontuschka.com',"+vaga+",1)");
			
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
  }
}