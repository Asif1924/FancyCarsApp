# FancyCarsApp
This app shows the use of a RecyclerView with Pagination


Imagine that you are hired by FancyCars.ca to build an App. In this App they want to:

1.	Show list of all the cars and for each car, they want to show picture, name, make, model and availability of the car. 
2.	Your app should support infinite scroll for the listing of the cars
3.	Have a sort Dropdown which can sort the results by both the name and availability of the car
4.	Show a buy button that only shows up if Availability is “In Dealership”
5.	Make sure your app can also work when its offline

Assume that the Backend will expose two services - AvailabilityService to get availability of the cars and CarService to get list of all the cars. 

API spec is as follows: 

GET /availability?id=123 
RESPONSE: {available: “In Dealership”}  // all  options are [ “Out of Stock”, “Unavailable”]

GET /cars
RESPONSE:  [ {id: 1, img: http://myfancycar/image, name: “My Fancy Car”, make: “MyMake”, model: “MyModel”, year: 2018} ….]

