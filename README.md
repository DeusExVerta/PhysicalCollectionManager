# PhysicalCollectionManager
A web application developed with Spring Boot and Thymeleaf for personal inventory management focused on valuable collectables.

## Security 
Security implemented with spring security

## Functionality overview
Application provides the ability for users to register to the service, 
create Inventories of items based on unique names and Item types. 
then fill those Inventories with individual Items (name,type,quantity,quality,location and price)

## Pages
- login 
  a basic login page
- register
  a basic registration page featuring form validation using javascript including email with regex.
- Inventorys
  this page displays a paginated table containing all of a users individual inventories as well as forms for adding item types and inventories
- SingleInventory (/Inventorys/{InventoryName})
  this page displays the contents of a single inventory as well as forms for adding locations and Inventory Items
- SingleItem (/Inventorys/{InventoryName}/{ItemName})
  this page displays the details for an individual Item.
- Locations
  this page displays the users set of locations and provides forms to manage those locations.
  
