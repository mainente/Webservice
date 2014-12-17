package br.com.inventarioweb.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;

import br.com.inventarioweb.factory.ConnectionFactory;
import br.com.inventarioweb.model.Inventinfo;
import br.com.inventarioweb.model.foto;
import br.com.inventarioweb.model.inventario;
import br.com.inventarioweb.model.local;
import br.com.inventarioweb.model.produto;

public class InventinfoDAO extends ConnectionFactory {

	private static InventinfoDAO instance;
	
	

	public static InventinfoDAO getInstance(){
		if(instance == null)
			instance = new InventinfoDAO();
		return instance;
	}
	

	public ArrayList<Inventinfo> listarTodosprod(String inventario_id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Inventinfo> invent = null;
		conexao = criarConexao();
		invent = new ArrayList<Inventinfo>();
		try {
			pstmt = conexao.prepareStatement("select produto._id,nome from produto,produto_invent,tipo_produto where tipo_produto._id=id_tipo and produto_invent.produto_id=produto._id and produto_invent.invent_id="+inventario_id);
			rs = pstmt.executeQuery();
			Inventinfo inventarioinf;
			while(rs.next()){
				 inventarioinf = new Inventinfo();
				
				inventarioinf.setId_produto(rs.getInt("_id"));
				inventarioinf.setNome_prod(rs.getString("nome"));
				invent.add(inventarioinf);
			}
			inventarioinf = new Inventinfo();
			
			inventarioinf.setId_produto(0);
			inventarioinf.setNome_prod("");
			invent.add(inventarioinf);
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return invent;
	}
	public ArrayList<Inventinfo> listarTodosprodanteirior(String local_id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conexao2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		ArrayList<Inventinfo> invent = null;
		conexao = criarConexao();
		invent = new ArrayList<Inventinfo>();
		try {

			pstmt2 = conexao.prepareStatement("select inventario._id from inventario,local where local._id=local_id and estado='fechado' and local._id="+local_id+"order by dt_criacao desc");
			rs2 = pstmt2.executeQuery();
			rs2.next();
			pstmt = conexao.prepareStatement("select produto._id,nome from produto,produto_invent,tipo_produto where tipo_produto._id=id_tipo and produto_invent.produto_id=produto._id and produto_invent.invent_id="+rs2.getString("_id"));
			rs = pstmt.executeQuery();
			Inventinfo inventarioinf;
			while(rs.next()){
				 inventarioinf = new Inventinfo();
				
				inventarioinf.setId_produto(rs.getInt("_id"));
				inventarioinf.setNome_prod(rs.getString("nome"));
				invent.add(inventarioinf);
			}
			inventarioinf = new Inventinfo();
			
			inventarioinf.setId_produto(0);
			inventarioinf.setNome_prod("");
			invent.add(inventarioinf);
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return invent;
	}
	public Inventinfo verificarprod(String inventario_id,String produto){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Inventinfo> invent = null;
		conexao = criarConexao();
		invent = new ArrayList<Inventinfo>();
		Inventinfo inventarioinf = null;
		try {
			pstmt = conexao.prepareStatement("select produto._id from produto,produto_invent where produto_invent.produto_id=produto._id and produto_invent.invent_id="+inventario_id+" and  produto_invent.produto_id="+produto);
			rs = pstmt.executeQuery();
			
			 inventarioinf = new Inventinfo();

			while(rs.next()){
				
				inventarioinf.setId_produto(rs.getInt("_id"));
				}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return inventarioinf;
	}
	public  void deletarinventproduto(Integer id_produto, Integer id_invent){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("Delete from produto_invent where produto_id="+id_produto+" and invent_id="+id_invent);
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("Erro ao deletar inventario: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
	}
	public  void inserirprodutoinvent(String produto_id,String invent_id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("insert into produto_invent(produto_id,invent_id) values("+"'"+produto_id+"'"+","+"'"+invent_id+"'"+")");

			pstmt.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("Erro ao inserir inventario: " + e+pstmt);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
	}
	
	public  void fecharinvent(Integer id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Date data=new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dt_fim=dateFormat.format(data);
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("UPDATE inventario SET estado='fechado', dt_fim="+"'"+dt_fim+"'"+ "WHERE _id="+id);

			pstmt.executeUpdate();
					
		} catch (Exception e) {
			System.out.println("Erro ao deletar inventario: " + e+pstmt);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
	}
	
	public Inventinfo AcharProdutoinvent(String id_prod,String id_invent){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> local = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		produto p = null;
		conexao = criarConexao();
		Inventinfo prod_invent = null;
		try {
			pstmt = conexao.prepareStatement("select _id from produto_invent where produto_id="+id_prod+"and invent_id="+id_invent);
			rs = pstmt.executeQuery();
			  prod_invent=new Inventinfo();
				rs.next();
					prod_invent.setId(rs.getString("_id"));
		
				
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return prod_invent;
	}
		 public static String encodeImage(byte[] imageByteArray) {
		        return Base64.encodeBase64URLSafeString(imageByteArray);
		    }

}



