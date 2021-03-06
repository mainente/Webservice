package br.com.inventarioweb.resource;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import sun.rmi.runtime.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.inventarioweb.DAO.InventDAO;
import br.com.inventarioweb.DAO.InventinfoDAO;
import br.com.inventarioweb.DAO.ProdutoDAO;
import br.com.inventarioweb.controller.ProdutoController;
import br.com.inventarioweb.controller.inventController;
import br.com.inventarioweb.controller.inventinfoController;
import br.com.inventarioweb.controller.localController;
import br.com.inventarioweb.model.Inventinfo;
import br.com.inventarioweb.model.inventario;
import br.com.inventarioweb.model.local;
import br.com.inventarioweb.model.foto;
import br.com.inventarioweb.model.produto;


@Path("/invent")


public class inventarioResource {

		@GET
		@Path("/listarTodos")
		@Produces("application/json")
		public ArrayList<local> listarTodos(){
			//new ClienteDAO().deletar(2);
			return new localController().listarTodos();
		}
		@GET
		@Path("/listarTodosinvent/{id}")
		@Produces("application/json")
		public ArrayList<inventario> listarTodosinvent(@PathParam("id") String id){
			//new ClienteDAO().deletar(2);
			return new inventController().listarTodosinvent(id);
		}
		@GET
		@Path("/listarTodosprod/{id}")
		@Produces("application/json")
		public ArrayList<Inventinfo> listarTodosprod(@PathParam("id") String id){
			//new ClienteDAO().deletar(2);
			return new inventinfoController().listarTodosprod(id);
		}
		@GET
		@Path("/listarTodosprodanterior/{id}")
		@Produces("application/json")
		public ArrayList<Inventinfo> listarTodosprodanterior(@PathParam("id") String id){
			//new ClienteDAO().deletar(2);
			return new inventinfoController().listarTodosprodanterior(id);
		}
		
		@GET
		@Path("/verificarprod/{id}/{produto}")
		@Produces("application/json")
		public Inventinfo Verificarprod(@PathParam("id") String id,@PathParam("produto") String produto){
			//new ClienteDAO().deletar(2);
			return new inventinfoController().Verificarprod(id, produto);
		}
		@GET
		@Path("/infoinvent/{id_invent}")
		@Produces("application/json")
		public inventario infoinvent(@PathParam("id_invent") String id_invent){
			//new ClienteDAO().deletar(2);
			return new inventController().Infoinvent(id_invent);
		}
		@Path("/DeletarInvent/{id}")
		@DELETE
		@Produces("application/json")
		public void deletarinvent(@PathParam("id") int id){
			new InventDAO().deletarinvent(id);
			System.out.println("Enviando ");

		}
		@Path("/DeletarInventprod/{id_prod}/{id_invent}")
		@DELETE
		@Produces("application/json")
		public void deletarinventprod(@PathParam("id_prod") int id_prod,@PathParam("id_invent") int id_invent){
			new InventinfoDAO().deletarinventproduto(id_prod, id_invent);;
			System.out.println("Enviando ");

		}
		@Path("/InserirInventprod/{id_prod}/{id_invent}")
		@POST
		@Produces("application/json")
		public void inseririnventprod(@PathParam("id_prod") String id_prod,@PathParam("id_invent") String
				id_invent){
			new InventinfoDAO().inserirprodutoinvent(id_prod, id_invent);;
			System.out.println("Enviando ");

		}
		@Path("/FecharInvent/{id}")
		@PUT
		@Produces("application/json")
		public void fecharinvent(@PathParam("id") int id){
			new InventinfoDAO().fecharinvent(id);
			System.out.println("Enviando ");

		}
		@Path("/InserirInvent")
		@POST
		@Produces("application/json")
		

		public void inseririnvent(String invent){
			System.out.println(invent);

			 Gson gson = new Gson();
			 inventario  inv= gson.fromJson(invent, inventario.class);

			
				new InventDAO().inseririnvent(inv);



		}
		@Path("/salvarfoto/")
		@POST
		@Produces("application/json")
	 //   @Consumes(MediaType.APPLICATION_JSON)
		

		public void salvarfoto(String foto){
			System.out.println(foto);

			 Gson gson = new Gson();
			 foto f= gson.fromJson(foto, foto.class);
			 new ProdutoDAO().imagem(f);
		



		}
		
		@GET
		@Path("/produto/{id}")
		@Produces("application/json")
		public produto AcharProduto(@PathParam("id") String id){
			//new ClienteDAO().deletar(2);
			return new ProdutoController().AcharProduto(id);
		}
		@GET
		@Path("/foto/{id}")
		@Produces("application/json")
		public foto foto(@PathParam("id") String id){
			//new ClienteDAO().deletar(2);
			return new ProdutoController().foto(id);
		}
		@GET
		@Path("/produtoinventachar/{id_invent}/{id_prod}")
		@Produces("application/json")
		public Inventinfo AcharProdutoinvent(@PathParam("id_invent") String id_invent,@PathParam("id_prod") String id_prod){
			//new ClienteDAO().deletar(2);
			return new inventinfoController().AcharProdutoinvent(id_invent, id_prod);
		}
		
		@GET
		@Path("/produtocod/{codigo_barra}")
		@Produces("application/json")
		public produto AcharProdutocod(@PathParam("codigo_barra") String codigo_barra){
			//new ClienteDAO().deletar(2);
			return new ProdutoController().AcharProdutocod(codigo_barra);
		}
		

}