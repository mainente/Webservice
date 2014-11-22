package br.com.inventarioweb.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.inventarioweb.factory.ConnectionFactory;
import br.com.inventarioweb.model.inventario;
import br.com.inventarioweb.model.local;

public class InventDAO extends ConnectionFactory {

	private static InventDAO instance;
	
	

	public static InventDAO getInstance(){
		if(instance == null)
			instance = new InventDAO();
		return instance;
	}
	

	public ArrayList<inventario> listarTodosinvent(String idlocal){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<inventario> invent = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;

		conexao = criarConexao();
		invent = new ArrayList<inventario>();
		try {
			pstmt = conexao.prepareStatement("select * from inventario where local_id="+idlocal);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				inventario inventario = new inventario();
				
				inventario.setId(rs.getInt("_id"));
				inventario.setNome(rs.getString("nome"));
				inventario.setEstado(rs.getString("estado"));
				invent.add(inventario);
			}
			inventario inventario = new inventario();

			inventario.setId(0);;
			inventario.setNome("");
			inventario.setEstado("");
			invent.add(inventario);
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return invent;
	}
	public  void deletarinvent(Integer id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("Delete from inventario where _id="+id);
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("Erro ao deletar inventario: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
	}
	public  void inseririnvent(inventario invent){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("insert into inventario(nome,dt_criacao,descricao,estado,local_id) values("+"'"+invent.getNome()+"'"+","+"'"+invent.getDt_criacao()+"'"+","+"'"+invent.getDescricao()+"'"+","+"'"+invent.getEstado()+"'"+","+"'"+invent.getId_local()+"'"+")");

			pstmt.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("Erro ao deletar inventario: " + e+pstmt);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
	}
	

}
