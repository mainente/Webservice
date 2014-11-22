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
				inventarioinf.setNome_prod(rs.getString("nome"));
				}
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return inventarioinf;
	}
	public  void deletar(Integer id){
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<local> clientes = null;
		
		conexao = criarConexao();
		try {
			pstmt = conexao.prepareStatement("Delete from cliente where id="+id);
			rs = pstmt.executeQuery();
		
					
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
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
	public ArrayList<foto> imagem(String inventario_id){
		 String imageDataString = null;
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<foto> f = null;
		conexao = criarConexao();
		
		f = new ArrayList<foto>();
		try {
		//	pstmt = conexao.prepareStatement("select _id,foto from produto,produto_invent where produto_invent.produto_id=produto._id and produto_invent.invent_id="+inventario_id);
			//rs = pstmt.executeQuery();
			Inventinfo inventarioinf;
			//while(rs.next()){
				// inventarioinf = new Inventinfo();
				
				//inventarioinf.setId_produto(rs.getInt("_id"));
				//inventarioinf.setNome_prod(rs.getString("nome"));
				//invent.add(inventarioinf);
			//}
			//inventarioinf = new Inventinfo();
			
			//inventarioinf.setId_produto(0);
			//inventarioinf.setNome_prod("");
			//invent.add(inventarioinf);
			foto fo=new foto();
			 File file = new File("/home/mainente/Imagens/cadeiranova.png");
			 
		        try {           
		            // Reading a Image file from file system
		            @SuppressWarnings("resource")
					FileInputStream imageInFile = new FileInputStream(file);
		            byte imageData[] = new byte[(int) file.length()];
		            imageInFile.read(imageData);
		 
		            // Converting Image byte array into Base64 String
		             imageDataString = encodeImage(imageData);
		             fo.setNome(imageData);
		             fo.setId(2);
		             f.add(fo);
		            System.out.println("Image Successfully Manipulated!");
		        } catch (FileNotFoundException e) {
		            System.out.println("Image not found" + e);
		        } catch (IOException ioe) {
		            System.out.println("Exception while reading the Image " + ioe);
		        }
			
		} catch (Exception e) {
			System.out.println("Erro ao listar todos os clientes: " + e);
			e.printStackTrace();
		} finally {
			fecharConexao(conexao, pstmt, rs);
		}
		return f;
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
				while(rs.next()){
					prod_invent.setId(rs.getString("_id"));
		
				}
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



