-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2018 at 02:58 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mrdelivery`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `cart_Id` int(11) NOT NULL,
  `user_Id` int(11) DEFAULT NULL,
  `item_name` text,
  `item_desc` text,
  `item_price` text,
  `quantity` text,
  `total` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `delivery_info`
--

CREATE TABLE `delivery_info` (
  `address_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `street_address` text NOT NULL,
  `city` text NOT NULL,
  `state` text NOT NULL,
  `zipcode` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `delivery_info`
--

INSERT INTO `delivery_info` (`address_id`, `user_id`, `street_address`, `city`, `state`, `zipcode`, `date`, `time`) VALUES
(1, 4, '1300 University Road', 'Alabama', 'Califonia', 90059, '2018-01-31', '13:30:00'),
(24, 4, '547 North Road', 'Sandton', 'Gauteng', 2010, '2018-01-16', '12:00:00'),
(25, 4, '745 Email Str', 'Tennasee', 'Texas', 44544, '2018-01-17', '13:00:00'),
(26, 4, '547 Email Street', 'Madison', 'Madison WI', 54788, '2018-01-19', '17:30:00'),
(27, 4, '24 Cold War Boulevard', 'Las Vegas', 'Vietnam', 5472, '2018-01-19', '13:00:00'),
(28, 4, '458 Harlem Str', 'Little Maxico', 'Gauteng', 54687, '2018-01-20', '17:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `restaurant_id` int(11) NOT NULL,
  `section_name` text,
  `item_name` text,
  `item_desc` text,
  `item_price` text,
  `menu_image` longtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `restaurant_id`, `section_name`, `item_name`, `item_desc`, `item_price`, `menu_image`) VALUES
(10, 4, 'Lunch', 'Martinez', 'Non-alcoholic drink', '10.00', NULL),
(15, 4, 'Lunch', 'Traditional Wing Party Platter', '', '62.70', NULL),
(16, 4, 'Lunch', 'Boneless Wing Party Platter', '', '70.00', NULL),
(17, 3, 'Appetizers', 'Boneless Wings', 'Hand-tossed in choice of sauce.', '10.19', NULL),
(18, 3, 'Appetizers', 'Smoked-wings', 'Bone-in wings slow smoked in-house over pecan wood', '9.99', NULL),
(19, 3, 'Starters', 'Southwestern Eggrolls', 'These aren\'t your ordinary eggrolls.  Crispy flour tortilllas, chicken and black beans. Served with avocado ranch.', '8.99', NULL),
(20, 3, 'Meals', 'T-Bone Steak', 'Smothered in white queso', '51.41', NULL),
(21, 4, 'Drinks', 'Fanta', '500ml', '2.00', NULL),
(22, 3, 'Breakfast', 'Dagwood', 'Two beef paddies, tomato, three slices of toasted bread, slice of gouda cheese etc', '31.00', NULL),
(23, 12, 'Lunch', 'Chicken Curry', 'Boneless peices and special blend of spices', '72.10', NULL),
(25, 1, 'Whooper', 'Cheese Sandwich', 'Since 1957, the WHOPPER®’s successful recipe has not changed. Freshly flame-grilled beef and fresh ingredients guarantee the incomparable taste.', '37.90', NULL),
(26, 1, 'Whooper', 'Fierce Whooper', 'The classic WHOPPER® just got Fierce! Featuring fiery chilli mayo, angry onions and zesty jalapeño slices, this is one spicy mouthful!', '42.90', NULL),
(27, 1, 'Beef Burger', 'Big King™ XXL', 'Extra-extra-large. That isn\'t a size description - it’s a promise!', '49.90', NULL),
(28, 1, 'Sides', 'Chicken Wings', 'Our perfectly spiced chicken wings are great for sharing. Available in a 8-piece or 12-piece pack', '23.90', NULL),
(29, 1, 'Sides', 'King Fries', 'Our KING Fries are exactly how fries should be: made from fresh potatoes, golden in colour, and crisp', '18.90', NULL),
(30, 1, 'Chicken Burger', 'Crispy Chicken Sandwich', 'Crispy on the outside, soft on the inside.', '44.90', NULL),
(31, 1, 'Chicken Burger', 'Chilli Cheese Chicken Sandwich', 'Add some spice to an Original Chicken and choose the Chilli Cheese Chicken, smothered in chilli cheese sauce and a generous helping of fiery jalapeños.', '40.90', NULL),
(32, 1, 'Chicken Burger', 'Chicken Tendercrisp® with Cheese', 'Sandwich is made with a premium chicken breast fillet, generously breaded with homemade seasoning and carefully layered with fresh lettuce, two slices of cheese, ripe juicy tomatoes, and creamy mayonnaise between a corn dusted bun.', '44.90', NULL),
(33, 10, 'Breakfast', 'Brekkie Crunch Wrap', 'A delicious spicy mini fillet (Crunch fillet) egg and cheese with grilled tomato sauce all wrapped up in a mini tortilla.', '26.90', NULL),
(34, 10, 'Breakfast', 'a.m. Deluxe Burger With Egg', 'Succulent Original Recipe® chicken fillet, topped with a hashbrown, cheese slice, tomato sauce, creamy dressing and an egg in a soft bun.', '42.90', NULL),
(35, 10, 'Breakfast', 'a.m. Toasties - Chicken & Mayonnaise', 'Toasted Chicken Mayonaise on either White or Brown bread.', '21.90', NULL),
(36, 10, 'Street Wise', 'Streetwise One Mash', '1 Piece of chicken cooked to golden perfection, a warm mini loaf and large mash & delicious gravy.', '25.90', NULL),
(37, 10, 'Street Wise', 'Fully Loaded Box Meal (Original)', 'Colonel Burger, 1 piece of chicken cooked to golden perfection, regular mash and gravy, regular chips & an ice-cold drink', '72.90', NULL),
(38, 8, 'Salads', 'Chicken permansan salad', 'With avo and balsamic reduction', '65.50', NULL),
(39, 6, 'Meals', 'Buff Chick', 'Bonesless Crispy Chicken, Runch and Buffalo Sauce', '95.45', NULL),
(40, 5, 'Meals', 'Uncle Sal\'s Spectacular', 'Gouda Cheese with Italian Sausage, Peperonni and Ground Beef', '105.00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `item_name` text,
  `item_desc` text,
  `item_price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `status` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `item_name`, `item_desc`, `item_price`, `quantity`, `total`, `status`) VALUES
(4, 4, 'Boneless Wings', 'Boneless Wings', 10.19, 2, 20.38, 'Order Placed'),
(5, 4, 'Smoked-wings', 'Smoked-wings', 9.99, 10, 99.9, 'Delivered');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `credit_card_number` bigint(15) NOT NULL,
  `expiry_date` date NOT NULL,
  `cv_code` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`payment_id`, `user_id`, `credit_card_number`, `expiry_date`, `cv_code`) VALUES
(1, 4, 456456456456, '2018-01-26', 4569),
(2, 4, 789545512454, '2018-01-18', 5478),
(3, 4, 545645646546, '2018-01-19', 5454),
(4, 4, 456987522254, '2018-01-26', 54566);

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `restaurant_id` int(11) NOT NULL,
  `restaurant_name` text,
  `restaurant_address` text,
  `restaurant_city` text,
  `restaurant_zipcode` text,
  `restaurant_phone_num` text,
  `restaurant_fax_num` text,
  `owner_name` text,
  `cellphone_num` text,
  `email_address` text,
  `hrs_open` time DEFAULT NULL,
  `hrs_close` time DEFAULT NULL,
  `special_instructions` text,
  `info_supply` text,
  `package_type` text,
  `request_status` tinyint(1) DEFAULT '0',
  `logo` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`restaurant_id`, `restaurant_name`, `restaurant_address`, `restaurant_city`, `restaurant_zipcode`, `restaurant_phone_num`, `restaurant_fax_num`, `owner_name`, `cellphone_num`, `email_address`, `hrs_open`, `hrs_close`, `special_instructions`, `info_supply`, `package_type`, `request_status`, `logo`) VALUES
(1, 'Burger King', 'Sandhurst Centre, 60 Rivonia Rd,', 'Sandhurst, Johannesburg', '2124', '021 412 2557', '021 412 2557', 'Burger King Inc.', 'N/A', 'N/A', '10:00:00', '19:00:00', 'None', 'Google', 'Diamond', 1, 'Burger_King_Logo.png'),
(3, 'Hardrock', '11 W 53rd St', 'New York', '10019', '12123106600', '12123106600', 'Freddy Brown', '12155108700', 'mrbrown@hardrock.com', '18:00:00', '02:00:00', 'N/A', 'Restaurant Referral', 'Gold', 1, 'Hard-Rock-Cafe-Logo-1.jpg'),
(4, 'Famous Dave\'s', '900 S Park St', 'Madison, WI', '53715-1834', '1547895220', '1547895220', 'Yacoub Harrison', '1336898742', 'harrison@gmail.com', '07:00:00', '23:00:00', 'N/A', 'Email', 'Diamond', 1, '6612.png'),
(5, 'Rocky Rocco', '3880 Rib Mountain Dr', 'Wausau', '54401', '1457895364', '1457895364', 'Samuel Eto', '1457895364', 'eto@gmail.com', '09:00:00', '17:00:00', 'N/A', 'Google', 'Titanium', 1, 'rockysquarelogo_400x400.jpg'),
(6, 'Sweeto Burrito', '1423 W Appleway Ave', 'Coeur d Alene', '83814', '54879652105', '54879652105', 'Benny Jesus', '54879652105', 'sweetjesus@gmail.com', '09:00:00', '17:00:00', 'N/A', 'Email', 'Diamond', 1, 'd39577b5bbb266bff8037de4a137289f_400x400.jpeg'),
(8, 'Rustic', '309 Sherman Ave', 'Coeur d Alene', '83814', '8381483814', '8381483814', 'Bredon Sherman', '8381483814', 'sheer@mrdelivery.com', '11:00:00', '03:30:00', 'N/A', 'Facebook', 'Platinum', 1, 'rustic.png'),
(10, 'KFC', '82 Homestead & Bram Fischer Road, Sandton View Shopping Centre,', 'Bryanston, Kensington', '2191', '011 463 2493', '011 463 2493', 'Steph Kightley', '5200052000', 'kightleyr@gmail.com', '07:00:00', '22:00:00', 'Show calories on ingredients', 'Google', 'Gold', 1, '1200px-KFC_logo.svg.png'),
(12, 'Mocha Mommas', '154 Rovonia Road', 'Sandton', '2010', '0114564569', '0114564569', 'Johnson Jeske', '0795454563', 'jjeske@gmail.com', '11:00:00', NULL, 'N/A', 'Email', 'Platinum', 1, 'mocha momma.png'),
(13, 'Firehouse Subs', '1403 Email st', 'Madison WI', '53713', '014758654', '014758654', 'Christian Eriksen', '0815456875', 'cerik@gmail.com', '04:02:00', '13:00:00', 'N/A', 'Google', 'Titanium', 1, 'Firehouse.png'),
(14, 'Parkway Family Restaurant', '1221 Ann St', 'Madison, WI', '53713', '0115587865', '0115587865', 'Derick Rose', '0795587865', 'derickrose@fhr.com', '13:00:00', '02:00:00', 'N/A', 'Mail', 'Platinum', 0, '4971.png');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role`) VALUES
(1, 'admin'),
(2, 'customer');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `active` int(11) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `active`, `email`, `last_name`, `name`, `password`) VALUES
(3, 1, 'admin@mrdelivery.com', 'Mmatli', 'Tshepo', '$2a$10$8kRsiX5fe5EOCSYEpePJgOJqDJ3Vm1Nra5bK7Aqib0AeEFUEBcyqS'),
(4, 2, 'tshepo@mrdelivery.com', 'Mmatli', 'Tshepo', '$2a$10$Q6YT11Xe8LJNB.4GCR1lI.3AaUG/GpCbu7MW9T.oIP.wIZciBTj1a');

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(3, 1),
(4, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`cart_Id`);

--
-- Indexes for table `delivery_info`
--
ALTER TABLE `delivery_info`
  ADD PRIMARY KEY (`address_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`restaurant_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `cart_Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `delivery_info`
--
ALTER TABLE `delivery_info`
  MODIFY `address_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `restaurant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
