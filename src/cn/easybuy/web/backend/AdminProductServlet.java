package cn.easybuy.web.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.easybuy.entity.Product;
import cn.easybuy.entity.ProductCategory;
import cn.easybuy.service.product.ProductCategoryService;
import cn.easybuy.service.product.ProductCategoryServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.Pager;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.web.AbstractServlet;



@WebServlet(urlPatterns = {"/admin/product"},name = "adminProduct")
public class AdminProductServlet extends AbstractServlet {

	private ProductService productService;
	private ProductCategoryService productCategoryService ;
	
	@Override
	public void init() throws ServletException {
		productService = new ProductServiceImpl();
		productCategoryService = new ProductCategoryServiceImpl();
	}
	
	
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String currentPageStr =request.getParameter("currentPage");
		int currentPage = EmptyUtils.isNotEmpty(currentPageStr)?Integer.parseInt(currentPageStr):1;
		int rowCount = productService.queryProductCount(null);
		Pager pager = new Pager(rowCount,6,currentPage);
		pager.setUrl("admin/product?action=index");
		List<Product> productList = productService.getAllProduct(pager);
		request.setAttribute("productList", productList);
		request.setAttribute("pager", pager);
		request.setAttribute("menu", 5);
		return "/backend/product/productList"; 
	}
	/**
	 * 删除商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ReturnResult deleteById(HttpServletRequest request,HttpServletResponse response)throws Exception{
		ReturnResult result=new ReturnResult();
		String id=request.getParameter("id");
		int ok = productService.deleteById(Integer.parseInt(id));
		if (ok>0) {
			result.returnSuccess();
		}else{
			result.returnFail("删除失败");
		}
	
		return result;
	}
	/**
	 * 修改商品
	 * @return
	 */
	public String toUpdateProduct(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//根据id查询商品  显示分类
		String id = request.getParameter("id");
		Product product = productService.getDetailById(Integer.parseInt(id));
		List<ProductCategory>  productCategoryList1 = productCategoryService.queryCategory(1);
		List<ProductCategory>  productCategoryList2 = productCategoryService.queryCategory(2);
		List<ProductCategory>  productCategoryList3 = productCategoryService.queryCategory(3);
		
		
		request.setAttribute("product", product);
    	request.setAttribute("productCategoryList1", productCategoryList1);
    	request.setAttribute("productCategoryList2", productCategoryList2);
    	request.setAttribute("productCategoryList3", productCategoryList3);
		request.setAttribute("menu",6);
		return "/backend/product/toAddProduct";
	}
	
	/**
	 * 去添加商品
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String toAddProduct (HttpServletRequest request,HttpServletResponse response)throws Exception{
		//查询出一级分类
        List<ProductCategory> productCategoryList=productCategoryService.queryCategory(1);
        request.setAttribute("productCategoryList1",productCategoryList);
		request.setAttribute("menu", 6);
		return "/backend/product/toAddProduct";
	}
	/**
	 * 添加商品
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void addProduct(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String uploadFilePath = request.getSession().getServletContext().getRealPath("files");
		DiskFileItemFactory disk = new DiskFileItemFactory();
		ServletFileUpload load = new ServletFileUpload(disk);
		Product product = new Product();
	
		try{
			List<FileItem> list = load.parseRequest(request);
			for (FileItem fileItem : list) {
				if(fileItem.isFormField()){
					String fieldName = fileItem.getFieldName();
					if(fieldName.equals("categoryLevel1Id")){
						product.setCategoryLevel1Id(Integer.parseInt(fileItem.getString("UTF-8")));
					}
					
					if(fieldName.equals("categoryLevel2Id")){
						product.setCategoryLevel2Id(Integer.parseInt(fileItem.getString("UTF-8")));
					}
					
					if(fieldName.equals("categoryLevel3Id")){
						product.setCategoryLevel3Id(Integer.parseInt(fileItem.getString("UTF-8")));
					}
					
					if(fieldName.equals("name")){
						product.setName(fileItem.getString("UTF-8"));
					}
					
					if(fieldName.equals("price")){
						product.setPrice(Double.parseDouble(fileItem.getString("UTF-8")));
					}
					
					if(fieldName.equals("stock")){
						product.setStock(Integer.parseInt(fileItem.getString("UTF-8")));
					}
					
					if(fieldName.equals("description")){
						product.setDescription(fileItem.getString("UTF-8"));
					}
					
					if(fieldName.equals("id")){
						product.setId(EmptyUtils.isEmpty(fileItem.getString("UTF-8"))?-1:Integer.parseInt(fileItem.getString("UTF-8")));
					}
					
					
				}else{
					String fileName = fileItem.getName();
					String realPath = request.getServletContext().getRealPath("/");
					if(fileName !=null && !fileName.equals("")){
						File fullFile = new File(fileName);
						
						File savaFile = new File(uploadFilePath,fullFile.getName());
						File savaFile2 = new File(realPath+"files",fileName);
						fileItem.write(savaFile);
						fileItem.write(savaFile2);
						
						
					}
					product.setFileName(fileName);
				}
			}
			//如果id等于-1 执行新增
			if(product.getId()==-1){
				productService.addProduct(product);
			}else{
				productService.updateProduct(product);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath()+"/admin/product?action=index");
	}
	@Override
	public Class getServletClass() {
		return AdminProductServlet.class;
	}
}
