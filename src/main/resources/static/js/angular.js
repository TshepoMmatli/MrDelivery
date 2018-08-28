var mrDelivery = angular.module("myApp", []);
mrDelivery.controller("mrDeliveryController", function ($scope, $http)
{
    /*==========================================================================
    *                           Restaurants
    *==========================================================================*/

    // Submit A Restaurant Owner's Request
    $scope.processRequest = function ()
    {
        //GET the name of the file chosen using html
        var file = document.getElementById('img');
        var name = file.files.item(0).name; //Name is assigned to "name" variable
        
        //Prepare the data to be transported and store the data into an array
        var addRestaurant = {
            packageType: $scope.requestForm.packageType,
            restaurantName: $scope.requestForm.restaurant_name,
            restaurantAddress: $scope.requestForm.restaurant_address,
            restaurantZipcode: $scope.requestForm.restaurant_zipcode,
            cellphoneNum: $scope.requestForm.cellphone_num,
            restaurantCity: $scope.requestForm.restaurant_city,
            ownerName: $scope.requestForm.owner_name,
            emailAddress: $scope.requestForm.email_address,
            restaurantFaxNum: $scope.requestForm.restaurant_fax_num,
            restaurantPhoneNum: $scope.requestForm.restaurant_phone_num,
            hrsOpen: $scope.requestForm.hrs_open,
            hrsClose: $scope.requestForm.hrs_close,
            specialInstructions: $scope.requestForm.special_instructions,
            infoSupply: $scope.requestForm.info_supply,
            logo: name,
            requestStatus: 0
        };        
        console.log(addRestaurant);
        
        //POST addRestaurant's data to the RestaurantController
        $http({
            method: 'POST',
            url: 'http://localhost:8080/SaveRestaurant',
            data: angular.toJson(addRestaurant),
        })
        .success(function (data) {
            alert("Restaurant Information submitted");
        });
    };
    
    //HTTP GET- Get Pending Restaurants
    $scope.pendingRestaurants = [];
    var pendingRestaurants;
    function refreshPendingRestaurantData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/GetPending',
            data: angular.fromJson(pendingRestaurants)
        }).success(function (data) {
            $scope.pendingRestaurants = data;
        });
    }
    
    //HTTP GET- Get Accepted Restaurants
    $scope.restaurants = [];
    var restaurants;
    function refreshRestaurantData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/GetRestaurants',
            data: angular.fromJson(restaurants),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data) {
            $scope.restaurants = data;
        });
    };
    
    //Now load the data from server
    refreshPendingRestaurantData();
    refreshRestaurantData();
    
    //Pass the selected restaurant for further processing
    //Change restaurant request status to true
    $scope.acceptRestaurant = function (restaurant) {
        var updateRestaurant = {
            restaurantId: restaurant.restaurantId
        };

        console.log(updateRestaurant);

        $http({
            method: 'PUT',
            url: 'http://localhost:8080/updateRestaurant/' + restaurant.restaurantId,
            data: angular.toJson(updateRestaurant),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data) {
            $scope.restaurantController = data;
            alert("Restaurant Accepted.");
            refreshPendingRestaurantData();
            refreshRestaurantData();
            
        });
    };

    //Pass the selected restaurant for further processing
    //Delete the restaurant
    $scope.deleteRestaurant = function (restaurant) {
        var delRestaurant = {
            restaurantId: restaurant.restaurantId
        };

        console.log(delRestaurant);

        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteRestaurant/' + restaurant.restaurantId,
            data: angular.toJson(delRestaurant),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data) {
            $scope.restaurantController = data;
            alert("Restaurant Removed");
            refreshPendingRestaurantData();
            refreshRestaurantData();
        });
    };

    /*==========================================================================
    *                           End of Restaurants
    *==========================================================================*/
    /*==========================================================================
    *                           Menu
    *==========================================================================*/
    
    //Create Restaurant Menu
    $scope.restaurantData;
    $scope.restaurantId;
    var restId = 0;
    //Pass the selected restaurant for further processing
    //Opens "Create Menu Modal"
    $scope.createMenu = function (restaurant)
    {
        restaurantId = restaurant.restaurantId;
        restId = restaurant.restaurantId;
        
        var restaurantName = restaurant.restaurantName;
        $scope.restaurantId = restaurantId;
        var restaurantInfo =
                [{
                        restaurantId: restaurantId,
                        restaurantName: restaurantName
                    }];
        $scope.restaurantData = restaurantInfo;
        console.log($scope.restaurantId);
        $("#createMenuModal").modal('show');
        $("#ViewMenuSectionModal").modal('hide');
        $("#ViewMenuitemsModal").modal('hide');
    };
    
    //Save Restaurant Menu
    $scope.saveMenuItem = function ()
    {
        var menuItem = {
            restaurantId: $scope.restaurantId,
            sectionName: $scope.menuForm.menu_section,
            itemName: $scope.menuForm.item_name,
            itemDesc: $scope.menuForm.item_desc,
            itemPrice: $scope.menuForm.item_price
        };

        console.log(menuItem);

        $http({
            method: 'POST',
            url: 'http://localhost:8080/SaveMenuItem',
            data: angular.toJson(menuItem),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data) {
            $("#createMenuModal").modal('hide');
            location.reload(true);
        });
    };

    //View Restaurant Menu
    $scope.selectedRestaurantName;
    $scope.viewMenu = function (restaurant)
    {
        //Make sure that other modals are closed
        $("#createMenuModal").modal('hide');
        $("#ViewMenuitemsModal").modal('hide');
        
         //Get the selected restaurant ID and restaurant name
        restId = restaurant.restaurantId;
        $scope.selectedRestaurantName = restaurant.restaurantName;
        
        //GET Restaurant Menu data
        $http({
            method: 'GET',
            url: 'http://localhost:8080/GetMenu/' + restaurant.restaurantId,
            data: angular.fromJson(restaurants),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data)
        {
            //GET Sections available for the restaurant
            $scope.Sections; 
            var sections = [];
            for (var i = 0; i < data.length; i++)
            {
                //Populate sections into sections array
                sections[i] = data[i].sectionName;
                
                //Display what's being stored into the section array
                console.log("Storing " + data[i].sectionName);
            }
            
            //Declare a function to remove duplicated section names
            function removeDuplicateUsingFilter(arr) {
                let unique_array = arr.filter(function (elem, index, self) {
                    return index === self.indexOf(elem);
                });
                return unique_array;
            }

            $scope.Sections = removeDuplicateUsingFilter(sections); //Unique section names
            
            if($scope.Sections.length === null || $scope.Sections.length === 0)
                alert("Restaurant has no menu sections");
            else
                $("#ViewRestaurantModal").modal('hide');
                $("#ViewMenuSectionModal").modal('show');
                console.log($scope.Sections);
        });
    };
        
    //View Restaurant Menu 
    //Display Restaurant Menu items
    $scope.viewMenuItem = function (sectionName)
    {
        $scope.sectionName = sectionName;
        $scope.Menus;

        //Close the menu sections modal and open the menu items modal
        $("#ViewMenuSectionModal").modal('hide'); 
        $("#ViewMenuitemsModal").modal('show'); 
        
        //GET Restaurant Menu data
        $http({
            method: 'GET',
            url: 'http://localhost:8080/GetMenu/' + restId + "/" + sectionName,
            data: angular.fromJson(restaurants),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data)
        {
            $scope.Menus = data; //Successfully retrieved restaurant menu data
        });
    };

    //For Customers - Display Restaurant Menu items
    $scope.showMenuItem = function (sectionName)
    {
        calcPayableAmount(); //process necessary calculations 
        $scope.sectionName = sectionName;
        $scope.Menus;

        //Hide the menu section modal
        $("#ViewMenuSectionModal").modal('hide'); 

        //GET Restaurant Menu data
        $http({
            method: 'GET',
            url: 'http://localhost:8080/GetMenu/' + restId + "/" + sectionName,
            data: angular.fromJson(restaurants),
            headers: {'Content-Type': 'application/json'}
        })
        .success(function (data)
        {
            $scope.Menus = data; //Menus with Items are return into $scope.Menus 
        }); 
            
    };
    
    var itemCount = 0;
    $scope.ItemName;
    $scope.ItemPrice;
    $scope.quantity;
    
    $scope.processingFee = 0.00;
    $scope.deliveryFee = 0.00;
    $scope.tax = 0;
    $scope.subtotal = 0;
    
    //Customer selects an item from the menu (Create Order Page)
    $scope.selectItem = function(menu){ 
        calcPayableAmount(); //Make necessary calculations
        $scope.ItemName = menu.itemName;
        $scope.ItemPrice = menu.itemPrice;
        $scope.ItemDesc = menu.itemDesc;
        $scope.quantity = 1;
        $scope.totalAmount =  menu.itemPrice; 
        $scope.totalAmount = parseFloat(Math.round($scope.totalAmount * 100) / 100).toFixed(2);
        $("#ViewRestaurantModal").modal('hide');
        $("#ViewMenuSectionModal").modal('hide');
        $("#AddItemToCartModal").modal('show');  
        
    };

    //Triggered from the createOrder page
    //Forcing users to sign in first before adding items to cart
    $scope.login = function(){
        calcPayableAmount();
        window.location.href = "http://localhost:8080/login";
        
    };
    
    /*==========================================================================
    *                           End of Menu
    *==========================================================================*/
    /*==========================================================================
    *                           Cart
    *==========================================================================*/
    //Items are now added to the cart
    $scope.addItemToCart = function(){
        calcPayableAmount(); //Make necessary calculations

        //Variable Declarations
        $scope.userId;
        var user;
        
        //Get the user who's currently signed in
        $http({
            method: 'GET',
            url: 'http://localhost:8080/viewUser',
            data: angular.fromJson(user),
            headers: {'Content-Type': 'application/json'}
         }).success(function (data) {
            var userID =  data.id; //user's ID is found
              
            //Prepare data to be added to cart  
            var addToCart = {
                userId: userID,
                itemName: $scope.ItemName,
                itemDesc: $scope.ItemDesc,
                itemPrice: $scope.ItemPrice,
                quantity: $scope.quantity,
                total: $scope.totalAmount
            };
             
            console.log(addToCart); //console addToCart
             
            //Now POST the data to the CartController
            $http({
                method: 'POST',
                url: 'http://localhost:8080/saveCart',
                data: angular.toJson(addToCart),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data) {
                //Hide modal on success and make calculations
                $("#AddItemToCartModal").modal('hide');
                $scope.processingFee = 1.55;
                $scope.deliveryFee = 15.89;
                totalNumOfCartItems();
                calcPayableAmount();
            }); 
         });
    };

    //Declare a function calculate total number of cart items
    function totalNumOfCartItems(){
        //Declare a GET user id function
        $scope.Cart;
        $scope.userId;
        var user;
        
        //Find signed in userId
        $http({
            method: 'GET',
        url: 'http://localhost:8080/viewUser',
            data: angular.fromJson(user),
            headers: {'Content-Type': 'application/json'}
         }).success(function (data) {
            var userID =  data.id; // User ID found 
            
            //Now search for the user's cart
            $http({
                method: 'GET',
                url: 'http://localhost:8080/viewCart/' + userID,
                data: angular.fromJson(user),
                headers: {'Content-Type': 'application/json'}
             }).success(function (data) {
                $scope.Cart = data;
                if(data.length === 0 || data.length === null) //If no data is returned no additional fee
                {                    
                    $scope.processingFee = 0.00;
                    $scope.deliveryFee = 0.00;
                    $scope.tax = 0.00;
                    $scope.subtotal = 0.00;
                }
                else //if cart is found addtional fees are as follows
                {
                    $scope.processingFee = 1.55;
                    $scope.deliveryFee = 15.89;
                }
                var itemCount = 0;
                
                for(var item = 0; item < data.length; item++) //Loop through every cart item
                {
                    itemCount =  parseInt(itemCount) + parseInt(data[item].quantity); //Count quantity of items and store results in "itemCount"
                }
           
                if(itemCount > 0)   //if count is more than 0 display quantity else don't
                    $('#itemCount').html(itemCount).css('display', 'block');
                else
                    $('#itemCount').html('').css('display', 'none');
                    $('#cartItems').html('');
                 
            });     
        }); 
    }

    //Load Total number of cart items
    totalNumOfCartItems();
   
    //Increases the quantity of the item a user has select
    $scope.incrementValue = function()
    {
        var value = parseInt(document.getElementById('number').value, 10);
        value = isNaN(value) ? 0 : value;
        value++;
        document.getElementById('number').value = value; 
        $scope.quantity = value;
        
        //Displays the amount to be paid for the item
        $scope.totalAmount =  $scope.ItemPrice * $scope.quantity;
        $scope.totalAmount = parseFloat(Math.round($scope.totalAmount * 100) / 100).toFixed(2);
    };

    //Decreases the quantity of the item a user has select
    $scope.decrementValue = function()
    {
        var value = parseInt(document.getElementById('number').value, 10);
        value = isNaN(value) ? 0 : value;
        if(!(value < 2))
        value--;
        document.getElementById('number').value = value;
        $scope.quantity = value;
        
        //Displays the amount to be paid for the item
        $scope.totalAmount =  $scope.ItemPrice * $scope.quantity;
        $scope.totalAmount = parseFloat(Math.round($scope.totalAmount * 100) / 100).toFixed(2);
    };
    
    //Removes every item on the user's cart
    $scope.emptyCart = function()
    {
        //GET user id 
        $scope.userId;
        var user;
        $http({
            method: 'GET',
            url: 'http://localhost:8080/viewUser',
            data: angular.fromJson(user),
            headers: {'Content-Type': 'application/json'}
         }).success(function (data) {
            var userID =  data.id; //user ID found
            
            //Submit a DELETE request to the CartController
            $http({
                method: 'DELETE',
                url: 'http://localhost:8080/emptyCart/' + userID,
                data: angular.toJson(user),
                headers: {'Content-Type': 'application/json'}
             }).success(function (data) {
                $scope.processingFee = 0.00;
                $scope.deliveryFee = 0.00;
                $scope.amountDue = 0.00;
                $scope.subtotal = 0.00;
                $scope.tax = 0;
                totalNumOfCartItems();
                calcPayableAmount();
                console.log("Your cart is now empty!");
                alert("Your cart is now empty!");
            });     
        }); 
    };
    
    //Deletes a cart item
    $scope.DeleteItem = function (cartItem)
    {
        //Prepare delete request
        var delCart = {
            cartId: cartItem.cartId //selected cart item identified with the ID
        };
        console.log(cartItem.cartId);
        //Submits a DELETE request to the CartController
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteCart/' + cartItem.cartId,
            data: angular.toJson(delCart),
            headers: {'Content-Type': 'application/json'}
         }).success(function () {
            //Refresh Payable Amount and Number of Cart Items
            totalNumOfCartItems();
            calcPayableAmount();
        });
    };
    
    function calcPayableAmount(){
        //GET user id 
        $scope.Cart;
        $scope.userId;
        var user;
        $http({
            method: 'GET',
            url: 'http://localhost:8080/viewUser',
            data: angular.fromJson(user),
            headers: {'Content-Type': 'application/json'}
         }).success(function (data) {
            var userID =  data.id; //User Id found
            
            //GET User's Cart from server
            $http({
                method: 'GET',
                url: 'http://localhost:8080/viewCart/' + userID,
                data: angular.fromJson(user),
                headers: {'Content-Type': 'application/json'}
            }).success(function (data) {
                $scope.Cart = data;  //Server response with cart data
                
                if(data.length === 0 || data.length === null) //If response has no data no additional charges apply
                {                    
                    $scope.processingFee = 0.00;
                    $scope.deliveryFee = 0.00;
                    $scope.tax = 0.00;
                    $scope.amountDue = 0.00;
                    $scope.subtotal = 0.00;
                }
                else // additional charges apply
                {
                    $scope.processingFee = 1.55;
                    $scope.deliveryFee = 15.89;
                    var subtotal = 0.00;
                    for(var item = 0; item < data.length; item++)
                    {
                        //GET subtotal in a Float Datatype
                        subtotal =  parseFloat(subtotal) + parseFloat(data[item].total);
                    } 
                    $scope.subtotal = parseFloat(subtotal).toFixed(2); //subtotal is in two decimal places
                    $scope.subtotal = parseFloat(Math.round($scope.subtotal * 100) / 100).toFixed(2); // Currency format
                    $scope.tax = parseFloat(subtotal * 0.14).toFixed(2); //Calc tax at a 14% rate 

                    $scope.amountDue  = parseFloat($scope.subtotal) + parseFloat($scope.tax)
                            + parseFloat($scope.deliveryFee) + parseFloat($scope.processingFee); //Add all charges

                    $scope.amountDue = parseFloat(Math.round($scope.amountDue * 100) / 100).toFixed(2); //convert amountDue to currentcy format
                }
                
            });     
        }); 
    }
    
    //Now load Payable Amount on page execution
    calcPayableAmount();
    
    /*==========================================================================
    *                           End of Cart
    *==========================================================================*/
    /*==========================================================================
    *                           Orders
    *==========================================================================*/
    
    //Update the selected order status on click
    $scope.changeStatus = function(order){
        console.log(order.orderId);
        //GET the orderID then pass it to OrderController to be updated
        $http({
            method: 'PUT',
            url: '/changeStatus/' + order.orderId
        })
        .success(function () {
            refreshOrdersData();
        });
    };
    
    //Removes an order from the system
    $scope.deleteOrder = function(order)
    {
        //Submit a DELETE request to the OrderController
        $http({
            method: 'DELETE',
            url: 'http://localhost:8080/deleteOrder/' + order.orderId
        })
        .success(function () {
            alert("Order Removed");
            refreshOrdersData(); //Refresh Order Data
        });
    };
    
    //Place an order after deliveryInfo and payment details are saved 
    $scope.placeOrder = function()
    {
        //Find out who's currently logged in
        var user;
        $http({
            method: 'GET',
            url: 'http://localhost:8080/viewUser',
            data: angular.fromJson(user),
            headers: {'Content-Type': 'application/json'}
         }).success(function (data) {
            var userID =  data.id;

            //Upon finding out the logged in user,
            //Prepare Delivery Info data for transport
            var submitDeliveryInfo = {
                userId: userID,
                streetAddress: $scope.streetAddress,
                city: $scope.city,
                state: $scope.state,
                zipcode: $scope.zipcode,
                date: $scope.date,
                time: $scope.time
            }; 
            
            //Prepare payment details data for transport
            var paymentDetails = {
                userId: userID,
                creditCardNumber: $scope.creditCardNumber,
                expiryDate: $scope.expiryDate,
                cvCode: $scope.cvCode
            };
            
            console.log(submitDeliveryInfo);
            console.log(paymentDetails);
            //POST the delivery info 
            //along with the user id to the deliveryAddressController
            $http({
                method: 'POST',
                url: 'http://localhost:8080/saveDeliveryInfo',
                data: angular.toJson(submitDeliveryInfo),
                headers: {'Content-Type': 'application/json'}
            }).success(function () {
                console.log("Delivery Information Saved!");
            });
            
            //POST the payment info 
            //along with the user id to the paymentController
            $http({
                method: 'POST',
                url: 'http://localhost:8080/SavePayment',
                data: angular.toJson(paymentDetails),
                headers: {'Content-Type': 'application/json'}
            }).success(function () {
    
                console.log("Payment Successful!");
                
                //Create Move Cart Items to Orders Table
                //Get User's cart once again
                $http({
                    method: 'GET',
                    url: 'http://localhost:8080/viewCart/' + userID,
                    data: angular.fromJson(user),
                    headers: {'Content-Type': 'application/json'}
                }).success(function (data) {
                    $scope.Cart = data;  //Found User Cart! 
                    //
                    //Prepare Order Data
                    var orderItems = [];
                    for(var item = 0; item < data.length; item++)
                    {
                        orderItems = {
                            userId: userID,
                            itemName: data[item].itemName,
                            itemDesc: data[item].itemName,
                            itemPrice: data[item].itemPrice,
                            quantity: data[item].quantity,
                            total: data[item].total,
                            status: "Order Placed" 
                        };
                        
                        console.log(orderItems);
                    
                        //Now save the order by submitting a POST request to the server
                        $http({
                            method: 'POST',
                            url: 'http://localhost:8080/saveOrder',
                            data: angular.toJson(orderItems),
                            headers: {'Content-Type': 'application/json'}
                        }).success(function () {
                            console.log("Order Placed!");
                            
                            //lastly, empty the user's cart
                            $http({
                                method: 'DELETE',
                                url: 'http://localhost:8080/emptyCart/' + userID,
                                data: angular.toJson(user),
                                headers: {'Content-Type': 'application/json'}
                             }).success(function () {
                                
                                //Reload cart data and payable amount
                                totalNumOfCartItems();
                                calcPayableAmount();
                                console.log("Your cart is now empty!");
                                
                                //Redirect to Order Confirmation page
                                window.location.href = "http://localhost:8080/orderConfirmation";
                                
                            }); 
                        });
                    }
                });
                
            }); 
        });
        
    };
    
    //HTTP GET- get all orders
    $scope.AllOrders;
    var Orders;
    function refreshOrdersData() {
        $http({
            method: 'GET',
            url: 'http://localhost:8080/getAllOrders',
            data: angular.fromJson(Orders),
            headers: {'Content-Type': 'application/json'}
        }).success(function (data) {
            $scope.AllOrders = data;

            console.log
            (
            "==============================================\n\
            SHOWING ORDERS\n\
            ==============================================="
            );
            console.log($scope.AllOrders);
        });
    }
    //Now load order data from server
    refreshOrdersData();
    
    //Get Order Details 
    function getOrderDetails()
    {
        //Get the user currently logged in
        var user;
        $http({
            method: 'GET',
            url: 'http://localhost:8080/viewUser',
            data: angular.fromJson(user),
         }).success(function (data) {
            var userID =  data.id; //Current User Found
        
            //Now Get the current user's placed orders
            $http({
                method: 'GET',
                url: 'http://localhost:8080/getPlacedOrders/' + userID,
                data: angular.fromJson(user),
                headers: {'Content-Type': 'application/json'}
             }).success(function (data) {
                $scope.PlacedOrders =  data; //Placed Orders found
                
                console.log($scope.PlacedOrders);
                
                //Variable Declaration
                var tempTotal;
                var tempPrice;
                var PlacedOrders = [];
                
                for(var item = 0; item < data.length; item++) //loops through all placed orders
                {   
                    //convert both itemPrice and total to a currency format 
                    //and fix the values to two decimal places
                    tempPrice = parseFloat(Math.round(data[item].itemPrice * 100) / 100).toFixed(2);
                    tempTotal = parseFloat(Math.round(data[item].total * 100) / 100).toFixed(2);
                    
                    //Store data
                    PlacedOrders[item] = {
                      itemName: data[item].itemName,
                      itemDesc: data[item].itemDesc,
                      quantity: data[item].quantity,
                      total: tempTotal,
                      itemPrice: tempPrice
                    };
                }
                
                //Assign var PlaceOrders data to $scope.PlaceOrders
                $scope.PlacedOrders = PlacedOrders;
                
                //Variable declaration and initialization
                var orderSubtotal = 0.00;
                var orderAmountDue = 0.00;
                
                for(var item = 0; item < data.length; item++) //Loop through every order item returned from the server
                {
                    //Convert orderSubtotal to a float datatype
                    //And increment orderSubtotal with "total"
                    orderSubtotal =  parseFloat(orderSubtotal) + parseFloat(data[item].total); 
                    $scope.OrderID =  data[item].orderId;
                }
                
                //fix amounts to two decimal places and values are in float datatype
                $scope.orderSubtotal = parseFloat(orderSubtotal).toFixed(2);
                $scope.orderSubtotal = parseFloat(Math.round($scope.orderSubtotal * 100) / 100).toFixed(2);
                
                //Calculate tax
                $scope.orderTax = parseFloat(orderSubtotal * 0.14).toFixed(2);
                
                //Calculate the order's amount due (or Grand total some may say) 
                $scope.orderAmountDue  = parseFloat($scope.orderSubtotal) + parseFloat($scope.orderTax)
                        + parseFloat($scope.deliveryFee) + parseFloat($scope.processingFee);
                
                //Format the orderAmountDue accordingly
                $scope.orderAmountDue = parseFloat(Math.round($scope.orderAmountDue * 100) / 100).toFixed(2);
            });
        });
    };
    
    //Now load order details at runtime
    getOrderDetails();

    /*==========================================================================
    *                           End of Orders
    *==========================================================================*/
}); //End of RestaurantController
