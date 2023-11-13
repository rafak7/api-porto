package br.com.fiap.portoapi.model.repository;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.portoapi.model.Porto;
import jakarta.validation.Valid;

public class PortoRepository extends Repository {

	public static List<Porto> findAll() {

		String sql = "SELECT * FROM T_PS_CLIENTE";

		PreparedStatement ps = null;

		ResultSet rs = null;

		List<Porto> carros = new ArrayList<>();

		try {
			ps = getConnection().prepareStatement(sql);

			rs = ps.executeQuery();

			if (rs.isBeforeFirst()) {
				while (rs.next()) {

					Porto carro = new Porto();

					carro.setId(rs.getLong("ID_CLIENTE"));
					carro.setNome(rs.getString("NM_CLIENTE"));
					carro.setCpf(rs.getString("CPF_CLIENTE"));
					carro.setCpnj(rs.getString("CPNJ_CLIENTE"));
					carro.setData(rs.getDate("DT_NASC_CLIENTE").toLocalDate());
					carro.setEndereco(rs.getString("END_CLIENTE"));
					carro.setCidade(rs.getString("CIDADE_CLIENTE"));
					carro.setEstado(rs.getString("ESTADO_CLIENTE"));
					carro.setBairro(rs.getString("BAIRRO_CLIENTE"));

					carros.add(carro);
				}
			} else {
				System.out.println("Nao foram encontrados registros");
			}

		} catch (SQLException e) {
			System.out.println("Nao foi possivel listar: " + e.getMessage());
		}

		return carros;

	}
	
	public static Porto save(Porto porto) {
		
		String sql = "begin INSERT INTO T_PS_CLIENTE ("
				+ "    id_cliente,"
				+ "    nm_cliente,"
				+ "    cpf_cliente,"
				+ "    cpnj_cliente,"
				+ "    dt_nasc_cliente,"
				+ "    end_cliente,"
				+ "    cidade_cliente,"
				+ "    estado_cliente,"
				+ "    bairro_cliente"
				+ ") VALUES ("
				+ "    SQ_CLIENTE,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?,"
				+ "    ?"
				+ ");"
				+ " returning id into ?; end;";
		
		CallableStatement cs = null;
		
	try {
		cs = getConnection().prepareCall(sql);
		
		cs.setString(1, porto.getNome() );
		cs.setString(2, porto.getCpf() );
		cs.setString(3, porto.getCpnj() );
		cs.setDate(4, Date.valueOf(porto.getData()));
		cs.setString(5, porto.getEndereco() );
		cs.setString(6, porto.getCidade() );
		cs.setString(7, porto.getEstado() );
		cs.setString(8, porto.getBairro() );
		
		cs.registerOutParameter(9, java.sql.Types.INTEGER);
		cs.executeUpdate();
		porto.setId((long) cs.getInt(9));
		
		return porto;
		
	} catch (SQLException e) {
		System.out.println("Erro para salvar:" + e.getMessage());
	} finally {
		if(cs!=null)
			try {
				cs.close();
			} catch (SQLException e) {
				System.out.println("Nao foi possivel fechar: " + e.getMessage());
			}
	}
		
		return null;
	
	}

	public static boolean delete(Long portoId) {
		
		Porto porto = null;
		String sql = "DELETE FROM T_PS_CLIENTE where ID_CLIENTE = ?";
		PreparedStatement ps = null;
		
		porto = findById(portoId);
		
		if(porto == null) {
			return false;
		}

		try {
			ps = getConnection().prepareStatement(sql);
			
			ps.setLong(1, portoId);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Erro para deletar o item no banco de dados: " + e.getMessage());
		} finally {
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechas o PS: " + e.getMessage());
				}
		}
		
		return false;
	}

	
	public static Porto findById(Long id) {
		String sql = "SELECT * FROM T_PS_CLIENTE where ID_CLIENTE = ?";
		
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = getConnection().prepareStatement(sql);
			
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.isBeforeFirst()) {
				Porto carro = new Porto();
				while(rs.next()) {
					carro.setId(rs.getLong("ID_CLIENTE"));
					carro.setNome(rs.getString("NM_CLIENTE"));
					carro.setCpf(rs.getString("CPF_CLIENTE"));
					carro.setCpnj(rs.getString("CPNJ_CLIENTE"));
					carro.setData(rs.getDate("DT_NASC_CLIENTE").toLocalDate());
					carro.setEndereco(rs.getString("END_CLIENTE"));
					carro.setCidade(rs.getString("CIDADE_CLIENTE"));
					carro.setEstado(rs.getString("ESTADO_CLIENTE"));
					carro.setBairro(rs.getString("BAIRRO_CLIENTE"));
				}
				
				return carro;
			}
			
		} catch (SQLException e) {
			System.out.println("Erro para consultar o item no banco de dados: " + e.getMessage());
		}finally {
			if(ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechas o PS: " + e.getMessage());
				}
			
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechas o RS: " + e.getMessage());
				}

		}
		
		return null;
		
	}

	public static Porto update(@Valid Porto porto) {
	 
		String sql = "begin UPDATE T_PS_CLIENTE set NM_CLIENTE=?, CPF_CLIENTE=?, CPNJ_CLIENTE=?, DT_NASC_CLIENTE=?, END_CLIENTE=?, CIDADE_CLIENTE=?, ESTADO_CLIENTE=?, BAIRRO_CLIENTE=? where ID_CLIENTE = ? return ID_CLIENTE into ?; end;";
		
		CallableStatement cs = null;
		
		
		try {
			cs = getConnection().prepareCall(sql);
			
			cs.setString(1, porto.getNome() );
			cs.setString(2, porto.getCpf() );
			cs.setString(3, porto.getCpnj() );
			cs.setDate(4, Date.valueOf(porto.getData()));
			cs.setString(5, porto.getEndereco() );
			cs.setString(6, porto.getCidade() );
			cs.setString(7, porto.getEstado() );
			cs.setString(8, porto.getBairro() );
			cs.setLong(9, porto.getId());
			
			cs.registerOutParameter(10, java.sql.Types.INTEGER);
			cs.executeUpdate();
			
			porto.setId((long) cs.getInt(10));
			
			return porto;
			
		} catch (SQLException e) {
			System.out.println("Nao foi possivel atualizar no banco de dados: " + e.getMessage());
		} finally {
			if(cs!=null)
				try {
					cs.close();
				} catch (SQLException e) {
					System.out.println("Nao foi possivel fechar: " + e.getMessage());
				}
		}
		
		return null;
	}
	

}
