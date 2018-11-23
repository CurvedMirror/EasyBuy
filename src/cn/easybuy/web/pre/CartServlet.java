package cn.easybuy.web.pre;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import cn.easybuy.entity.Order;
import cn.easybuy.entity.Product;
import cn.easybuy.entity.User;
import cn.easybuy.entity.UserAddress;
import cn.easybuy.service.order.CartService;
import cn.easybuy.service.order.CartServiceImpl;
import cn.easybuy.service.order.OrderService;
import cn.easybuy.service.order.OrderServiceImpl;
import cn.easybuy.service.order.UserAddressService;
import cn.easybuy.service.order.UserAddressServiceImpl;
import cn.easybuy.service.product.ProductService;
import cn.easybuy.service.product.ProductServiceImpl;
import cn.easybuy.utils.Constants;
import cn.easybuy.utils.EmptyUtils;
import cn.easybuy.utils.ReturnResult;
import cn.easybuy.utils.ShoppingCart;
import cn.easybuy.utils.ShoppingCartItem;
import cn.easybuy.web.AbstractServlet;

@WebServlet(urlPatterns = { "/Cart" }, name = "Cart")
public class CartServlet extends AbstractServlet {

	private ShoppingCart shoppingCard;
	private ProductService productService; // 商品
	private CartService cartService;
	private UserAddressService userAddressService;
	private OrderService orderService;
	
	public void init() throws ServletException {
		shoppingCard = new ShoppingCart();
		productService = new ProductServiceImpl();
		cartService = new CartServiceImpl();
		userAddressService = new UserAddressServiceImpl();
		orderService = new OrderServiceImpl();
	}

	/**
	 * 添加购物车
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public ReturnResult addItems(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		ReturnResult result = new ReturnResult();
		String quantity = req.getParameter("quantity");
		String productIdStr = req.getParameter("productId");

		Integer productId = EmptyUtils.isNotEmpty(productIdStr) ? Integer
				.parseInt(productIdStr) : 0;

		// 查询出商品
		Product product = productService.getDetailById(productId);
		int stock = product.getStock() - Integer.parseInt(quantity);
		product.setStock(stock);
		
		if (product.getStock() < Integer.parseInt(quantity)) {
			return result.returnFail("商品数量不足");
		}
		// 存入作用域
		req.getSession().setAttribute("cart", shoppingCard);

		// 往购物车放置商品
		result = shoppingCard.addItem(product, Integer.parseInt(quantity));

		if (result.getStatus() == Constants.ReturnResult.SUCCESS) {

			shoppingCard
					.setSum((EmptyUtils.isEmpty(shoppingCard.getSum()) ? 0.0
							: shoppingCard.getSum())
							+ (product.getPrice() * Integer.parseInt(quantity) * 1.0));

		}
		return result.returnSuccess();
	}

	/**
	 * 修改购物车信息
	 * 
	 * @param request
	 * @return
	 */
	public ReturnResult modifyCart(HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		ReturnResult result = new ReturnResult();

		String productId = req.getParameter("entityId");
		String quantity = req.getParameter("quantity");

		// 查询出商品
		Product product = productService.getDetailById(Integer
				.parseInt(productId));
		if (EmptyUtils.isNotEmpty(quantity)) {
			if (Integer.parseInt(quantity) > product.getStock()) {
				return result.returnFail("商品数量不足");
			}
		}
		shoppingCard = cartService.modifyShoppingCart(productId, quantity,
				shoppingCard);
		req.getSession().setAttribute("cart", shoppingCard);// 全部的商品
		return result.returnSuccess();
	}

	public String refreshCart(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return "/common/pre/searchBar";
	}

	public String toSettlement1(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return "/pre/settLement/settlement1";
	}

	public String toSettlement2(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = (User) req.getSession().getAttribute("loginUser");
		int userId = user.getId();
		List<UserAddress> userAddressList = userAddressService
				.queryUserAddressList(userId);
		req.setAttribute("userAddressList", userAddressList);
		return "/pre/settLement/settlement2";
	}

	/**
	 * 确认订单
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String toSettlement3(HttpServletRequest req, HttpServletResponse resp)
			throws Exception {
		ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
		String addressId = req.getParameter("addressId");
		String newAddress = req.getParameter("newAddress");
		String newRemark = req.getParameter("newRemark");
		User user = (User)req.getSession().getAttribute("loginUser");
		UserAddress userAddress = new UserAddress();
		// 新增地址
		if (addressId.equals("-1")) {
			userAddress.setAddress(newAddress);
			userAddress.setRemark(newRemark);
			userAddress.setUserId(user.getId());
			userAddress.setId(userAddressService.addUserAddress(userAddress));
		}else{
			userAddress=userAddressService.getAddressById(Integer.parseInt(addressId));
		}
		//生成订单
		Order order = orderService.payShoppingCart(cart,user,userAddress.getAddress());
		clearCart(req, resp);
		cart.getItems().clear();
		cart.setSum(0);
        req.setAttribute("currentOrder", order);
		return "/pre/settLement/settlement3";
	}
	 /**
     * 清空购物车
     *
     * @param request
     * @param response
     */
    public ReturnResult clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReturnResult result = new ReturnResult();
        //结账后清空购物车
        request.getSession().removeAttribute("cart");
        
        result.returnSuccess(null);
        return result;
    }
	public String toSettlement(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		return "/pre/settLement/toSettlement";
	}

	@Override
	public Class getServletClass() {

		return CartServlet.class;
	}

}
