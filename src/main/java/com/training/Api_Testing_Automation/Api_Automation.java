package com.training.Api_Testing_Automation;

import java.util.HashMap;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Api_Automation {

	String sHostName = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
	String sEP_Login = "/login";
	String sEP_GetData = "/getdata";
	String sEP_AddData = "/addData";
	String sEP_DeleteData = "/deleteData";
	String sURL = "";
	String sToken = "";

	@Test(priority = 1)
	public void login_ValidCredentialsAndPasswords() {
		sURL = sHostName + sEP_Login;
		RestAssured.baseURI = sURL;

		
		HashMap<String, String> hmHeaderGetData = new HashMap<String, String>();
		hmHeaderGetData.put("Content-Type", "application/json");
		RequestSpecification requestParam = RestAssured.given();
		
		
		JSONObject body = new JSONObject();
		body.put("username", "shaila.nov20qa@ta.com");
		body.put("password", "shaila123");
		requestParam.headers(hmHeaderGetData);
		requestParam.body(body.toString());
		Response response = requestParam.post();
		sToken = response.jsonPath().getString("token[0]");
		response.prettyPrint();
	}

	@Test(priority = 2)
	public void getDataForValidToken() {
		sURL = sHostName + sEP_GetData;
		RestAssured.baseURI = sURL;
		HashMap<String, String> hmHeaderGetData = new HashMap<String, String>();
		hmHeaderGetData.put("token", sToken);
		hmHeaderGetData.put("Content-Type", "application/json");
		Response res = RestAssured.given().headers(hmHeaderGetData).get();
		System.out.println(res.statusCode());
		Assert.assertEquals(200, res.statusCode());
		
		
	}

	@Test(priority = 3)
	public void addData_ValidTokenandData() {
		sURL = sHostName + sEP_AddData;
		RestAssured.baseURI = sURL;
		HashMap<String, String> hmHeaderGetData = new HashMap<String, String>();
		hmHeaderGetData.put("token", sToken);
		hmHeaderGetData.put("Content-Type", "application/json");

		JSONObject requestParam = new JSONObject();
		requestParam.put("accountno", "863434");
		requestParam.put("departmentno", "011");
		requestParam.put("salary", "48900");
		requestParam.put("pincode", "455555");
		requestParam.put("userid", "muvI8FoTMGhQfxspPb1D");

		RequestSpecification request = RestAssured.given();
		request.headers(hmHeaderGetData);
		request.body(requestParam.toString());
		Response response = request.post();
		response.prettyPrint();
		System.out.println(response.statusCode());
		Assert.assertEquals(201, response.statusCode());

	}

	@Test(priority = 4)
	public void DeleteData_ValidTokenandData() {
		sURL = sHostName + sEP_DeleteData;
		RestAssured.baseURI = sURL;
		HashMap<String, String> hmHeaderGetData = new HashMap<String, String>();
		hmHeaderGetData.put("token", sToken);
		hmHeaderGetData.put("Content-Type", "application/json");

		JSONObject requestParam = new JSONObject();
		requestParam.put("accountno", "863434");
		requestParam.put("departmentno", "011");
		requestParam.put("salary", "48900");
		requestParam.put("pincode", "455555");
		requestParam.put("userid", "muvI8FoTMGhQfxspPb1D");
		requestParam.put("id", "rUhCj0hc8rXbbIuJ3AWl");

		RequestSpecification request = RestAssured.given();
		request.headers(hmHeaderGetData);
		request.body(requestParam.toString());
		Response response = request.delete();
		response.prettyPrint();
		System.out.println(response.statusCode());

	}
}
