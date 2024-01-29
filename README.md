Create a RESTful application “Super Duper Mart™ that is equipped with the necessary endpoints to return the needed information for the following requirements. Use Spring Boot, Hibernate, MySQL, Spring Security + JWT, Spring AOP, Spring Validation to develop the backend
User (Buyer)
1.	Registration：Before being able to purchase products, a user has to first register
2.	Login： If the user has entered the correct credentials, they may proceed to the corresponding page based on their authorities. If the user has entered incorrect credentials, a custom named exception ‘InvalidCredentialsException’ should be thrown and handled by the Exception handler。
3.	Home Page：The user is able to view all of the products and detail. After purchasing the product, the user should be able to view order details including order placement time and order status which is Processing, Completed or Canceled.
4.	Purchasing: The user should be able to purchase listing items with a specified quantity by creating a “Processing” order. The user should be able to cancel an order by updating the status from “Processing” to “Canceled. However, a “Completed” order cannot be changed to “Canceled”.
5.	Product Watchlist: The user can add/remove products to/from their watchlist.
6.	Summary: The user should be able to view all their orders. The user should be able to view their top 3 most frequently purchased items. The user can also view their top 3 most recently purchased items. 
Admin (Seller)
1.	Home Page: The seller should be able to view an Order information, with details of order placed time, users who placed the order and the order status (Processing, Processing, Canceled).
2.	Product: The seller should be able to add products and edit products and view all products
3.	Order: The seller should be able to complete a “Processing” order by updating its status to “Completed”. The seller can also be able to cancel an order by updating the order status to “Canceled” for some reasons. However, a “Canceled” order cannot be completed, nor can a “Completed” order be canceled
4.	Summary: The seller can see which product brings the top 3 most profit. The seller can also see which 3 products are the most popular/sold.
