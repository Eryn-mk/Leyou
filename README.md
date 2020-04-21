# Leyou E-Commerce Shopping Platform 乐优商城

## 1.

### 1.1.Project Intro.

-Leyou is a full-category e-commerce shopping website (B2C).
-Users can buy products online, add to cart, place orders and write reviews on purchased products
-Admin can manage listed products and promotions in the management system
-Admin can monitor the sales of goods


### 1.2.Architecture
- Overall Architecture     
![1525703759035](https://github.com/Eryn-mk/leyou/raw/photos/1525703759035.png)

- Leyou Management System：

  - Funtions：
    - Products，categories, brands, inventories
    - Sales，orders, refunds, promotions
    - Users: control, suspend or activate user accounts
    - Permission Controls: JWT with encryption
    - Statistics Dashboard
  - Frontend: A single page app with Vue.js and Vuetify
  - ![1573821246869](/raw/photos/1573821246869.png)

- Leyou Shopping Website
  - Client-side functions
    - Searching a product
    - Adding a product to cart
    - Placing an order
    - Writing a product review
  - Frontend: Thymeleaf template engine
  - ![1573821296666](/raw/photos/1573821296666.png)



### 1.3.Environment

Frontend：

- Basic HTML、CSS、JavaScript（ES6）
- JQuery
- Vue.js & Vuetify framework
- WebPack
- NPM
- Vue-cli
- vue-router
- ajax framework：axios
- Rich-text：quill-editor

Backend：

- Basic SpringMVC、Spring 5.0 & MyBatis3
- Spring Boot 2.x
- Spring Cloud Finchley.RC4
- Redis-4.x
- RabbitMQ-3.4
- Elasticsearch-5.6.8
- nginx-1.10.2：
- FastDFS - 5.0.8
- MyCat
- Thymeleaf



## 2.Structure

- [leyou](https://github.com/Eryn-mk/leyou)：Backend APIs
  - ly-registry：注册中心模块
  - ly-api-gateway：网关模块
  - ly-item：商品服务模块
  - ly-common：通用工具模块
  - ly-order：订单服务模块
- [leyou-manage-web](https://github.com/Eryn-mk/leyou-manage-web)：Frontend



## 3.Other materials

### 3.1.Database

[leyou.sql](/raw/db/leyou.sql)



### 3.2.Configurations

#### 3.2.1.hosts

```
# Leyou
127.0.0.1 api.leyou.com # API gateway Zuul
127.0.0.1 manage.leyou.com # management system
127.0.0.1 www.leyou.com # shopping website
```



#### 3.2.2nginx

```nginx
# leyou
server {
	listen       80;
	server_name  manage.leyou.com;

	proxy_set_header X-Forwarded-Host $host;
	proxy_set_header X-Forwarded-Server $host;
	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;

	location / {
		proxy_pass http://127.0.0.1:9001;
		proxy_connect_timeout 600;
		proxy_read_timeout 600;
	}
}
server {
	listen       80;
	server_name  api.leyou.com;

	proxy_set_header X-Forwarded-Host $host;
	proxy_set_header X-Forwarded-Server $host;
	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	# 转发时，携带自身的host，而不是转发后的host(127.0.0.1)
	proxy_set_header Host $host;

	# 上传路径的映射
	location /api/upload {	
		proxy_pass http://127.0.0.1:8082;
		proxy_connect_timeout 600;
		proxy_read_timeout 600;
		# 对请求路径进行重写 eg：/api/upload/image -> /upload/image
		rewrite "^/api/(.*)$" /$1 break; 
	}
	
	location / {
		proxy_pass http://127.0.0.1:10010;
		proxy_connect_timeout 600;
		proxy_read_timeout 600;
	}
}
# Shopping site
server {
	listen       80;
	server_name  www.leyou.com;

	proxy_set_header X-Forwarded-Host $host;
	proxy_set_header X-Forwarded-Server $host;
	proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
	
	location /item {
		# 先找本地
		root html/leyou;
		if (!-f $request_filename) { # 请求的文件不存在，就反向代理
			proxy_pass http://127.0.0.1:8084;
			break;
		}
	}
	
	location / {
		proxy_pass http://127.0.0.1:9002;
		proxy_connect_timeout 600;
		proxy_read_timeout 600;
	}
}
```
