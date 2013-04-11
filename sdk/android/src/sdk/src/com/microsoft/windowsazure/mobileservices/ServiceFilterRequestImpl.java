/*
Copyright (c) Microsoft Open Technologies, Inc.
All Rights Reserved
Apache 2.0 License
 
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at
 
     http://www.apache.org/licenses/LICENSE-2.0
 
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 
See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
/*
 * ServiceFilterRequestImpl.java
 */

package com.microsoft.windowsazure.mobileservices;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

/**
 * 
 * ServiceFilterRequest implementation
 * 
 */
class ServiceFilterRequestImpl implements ServiceFilterRequest {

	/**
	 * The HttpClient to use
	 */
	private DefaultHttpClient mHttpClient;

	/**
	 * The request to execute
	 */
	private HttpRequestBase mRequest;

	/**
	 * The request content
	 */
	private String mContent;

	/**
	 * Constructor
	 * 
	 * @param request
	 *            The request to use
	 */
	public ServiceFilterRequestImpl(HttpRequestBase request) {
		mRequest = request;
		mHttpClient = new DefaultHttpClient();
	}

	@Override
	public ServiceFilterResponse execute() throws Exception {
		// Execute request
		final HttpResponse response = mHttpClient.execute(mRequest);

		return new ServiceFilterResponseImpl(response);
	}

	@Override
	public Header[] getHeaders() {
		return mRequest.getAllHeaders();
	}

	@Override
	public void addHeader(String name, String val) {
		mRequest.addHeader(name, val);
	}

	@Override
	public void removeHeader(String name) {
		mRequest.removeHeaders(name);
	}

	@Override
	public void setContent(String content) throws UnsupportedEncodingException {
		((HttpEntityEnclosingRequestBase) mRequest).setEntity(new StringEntity(
				content, MobileServiceClient.UTF8_ENCODING));
		mContent = content;
	}

	@Override
	public String getContent() {
		return mContent;
	}

	@Override
	public String getUrl() {
		return mRequest.getURI().toString();
	}

	@Override
	public void setUrl(String url) throws URISyntaxException {
		mRequest.setURI(new URI(url));

	}

	@Override
	public String getMethod() {
		return mRequest.getMethod();
	}
	
	//TODO:TEST
	private ServiceFilterRequest mPreviousFilterRequest;
	private TableJsonOperationCallback mPreviousCallback;
	private TableJsonQueryCallback mPreviousQueryCallback;
	private TableDeleteCallback mPreviousDeleteCallback;
	private MobileServiceRequestType mPreviousCallType;
	private MobileServiceTableBase mPreviousMobileServiceTableBase;
	
	public ServiceFilterRequest getPreviousRequest() { return mPreviousFilterRequest; }
	public void setPreviousRequest(ServiceFilterRequest request) { this.mPreviousFilterRequest = request; }
	public TableJsonOperationCallback getPreviousCallback() { return mPreviousCallback; }
	public void setPrevoiusCallback(TableJsonOperationCallback callback) { this.mPreviousCallback = callback; }
	public TableJsonQueryCallback getPreviousQueryCallback() { return mPreviousQueryCallback; }
	public void setPreviousQueryCallback(TableJsonQueryCallback callback) { this.mPreviousQueryCallback = callback; }
	public TableDeleteCallback getPreviousDeleteCallback() { return this.mPreviousDeleteCallback; }
	public void setPreviousDeleteCallback(TableDeleteCallback callback) { this.mPreviousDeleteCallback = callback; }
	public MobileServiceRequestType getPreviousCalltype() { return this.mPreviousCallType; }
	public void setPreviousCalltype(MobileServiceRequestType calltype) { this.mPreviousCallType = calltype; }
	public MobileServiceTableBase getPreviousRequestTable() { return this.mPreviousMobileServiceTableBase; }
	public void setPreviousRequestTable(MobileServiceTableBase mobileServiceTableBase) { this.mPreviousMobileServiceTableBase = mobileServiceTableBase; }
	
}
