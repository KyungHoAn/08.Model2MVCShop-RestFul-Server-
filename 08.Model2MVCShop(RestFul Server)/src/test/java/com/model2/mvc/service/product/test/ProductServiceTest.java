package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
//==> meta-Data를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = {"classpath:config/commonservice.xml"})
//@ContexctConfiguration(locations={"classpath:config/context-*.xml"})
@ContextConfiguration(locations = {"classpath:config/context-aspect.xml",
								"classpath:config/context-common.xml",
								"classpath:config/context-mybatis.xml",
								"classpath:config/context-transaction.xml"})
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//ok
	//@Test
	public void testAddProduct() throws Exception{
		Product product = new Product();
		
		product.setProdName("testProductName3");
		product.setProdDetail("testProdDetail");
		product.setManuDate("111111");
		product.setPrice(123);
		product.setFileName("333333");
		
		System.out.println("sql add 완료");
		System.out.println(product);
		productService.addProduct(product);	
		product = productService.getProduct(10073);
		System.out.println(product);
		
		Assert.assertEquals("testProductName3", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("111111", product.getManuDate());
		Assert.assertEquals(123, product.getPrice());
		Assert.assertEquals("333333", product.getFileName());
	}
	//ok
	//@Test
	public void testGetProduct() throws Exception{
		Product product = new Product();
		
		product = productService.getProduct(10022);
		
		System.out.println(product);
		
		Assert.assertEquals("testProductName3", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("111111", product.getManuDate());
		Assert.assertEquals(123, product.getPrice());
		Assert.assertEquals("333333", product.getFileName());
		System.out.println("TestGet완료");
	}
	//ok
	//@Test
	public void testUpdateProduct() throws Exception{
		Product product = productService.getProduct(10022);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("testProductName3", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("111111", product.getManuDate());
		Assert.assertEquals(123, product.getPrice());
		Assert.assertEquals("333333", product.getFileName());
		
		product.setProdName("change");
		product.setProdDetail("changeDetail");
		product.setManuDate("99999");
		product.setPrice(321);
		product.setFileName("changeFileName");
		System.out.println("TestUpdate완료");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10022);
		
		System.out.println(product);
		
		Assert.assertEquals("change", product.getProdName());
		Assert.assertEquals("changeDetail", product.getProdDetail());
		Assert.assertEquals("99999", product.getManuDate());
		Assert.assertEquals(123, product.getProdNo());
		Assert.assertEquals("333333", product.getFileName());
	}
	
//	@Test
//	public void testCheckDuplication() throws Exception	{
//		System.out.println(ProductService.checkDuplication(""));
//	}
	
	//ok
	//@Test
	public void testGetProductListAll() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("==============================");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		totalCount=(Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//ok
	//@Test
	public void testGetProductListByProductNo() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("10065");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("============================");
		
		search.setSearchCondition("0");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//ok
	//@Test
	public void testGetProductListByProductName() throws Exception{
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("change");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list =(List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("================================");
		
		search.setSearchCondition("1");
		search.setSearchKeyword(""+System.currentTimeMillis());
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount =(Integer)map.get("totalCount");
		System.out.println(totalCount);
	}
}
