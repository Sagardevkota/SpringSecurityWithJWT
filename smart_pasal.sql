-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2021 at 10:43 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smart_pasal`
--

-- --------------------------------------------------------

--
-- Table structure for table `carts`
--

CREATE TABLE `carts` (
  `product_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `product_color` varchar(200) DEFAULT NULL,
  `product_size` float DEFAULT NULL,
  `price` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carts`
--

INSERT INTO `carts` (`product_id`, `user_id`, `id`, `product_color`, `product_size`, `price`) VALUES
(1, 26, 262, 'red', 42, '1800');

-- --------------------------------------------------------

--
-- Table structure for table `conversations`
--

CREATE TABLE `conversations` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `message` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `conversations`
--

INSERT INTO `conversations` (`id`, `user_id`, `message`, `date`, `product_id`) VALUES
(1, 26, 'Sir can this product be delivered at Pokhara?', '2020-06-12', 1),
(2, 27, 'Yes sir @Sagar,we can deliver this product to Pokhara', '2020-06-12', 1),
(4, 26, 'thank u for ur response sir!!', '2020-06-13', 1),
(6, 26, 'Sir,Can i get more discount?', '2020-06-13', 1),
(7, 26, 'Sir,Is this jacket made of pure leather?', '2020-06-14', 8),
(8, 26, 'sir can u deliver this product today?', '2020-07-02', 1),
(9, 27, 'No, Sir its already very cheap', '2020-07-10', 1),
(10, 27, 'But you can buy similar products which are less cheaper', '2020-07-10', 1);

-- --------------------------------------------------------

--
-- Table structure for table `coupons`
--

CREATE TABLE `coupons` (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `discount` int(11) NOT NULL,
  `coupon_code` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coupons`
--

INSERT INTO `coupons` (`id`, `product_id`, `discount`, `coupon_code`) VALUES
(1, 2, 10, 'smartRay');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `subject` text NOT NULL,
  `message` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `user_id`, `subject`, `message`) VALUES
(1, 26, 'Bad UI', 'please update your UI asap'),
(2, 26, 'nice app', 'good products and on time delivery!!!good job!!');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_color` varchar(255) DEFAULT 'none',
  `price` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `ordered_date` varchar(255) NOT NULL,
  `delivered_date` varchar(255) NOT NULL DEFAULT 'not delivered yet',
  `delivery_address` varchar(255) NOT NULL,
  `product_size` float DEFAULT 0,
  `status` varchar(255) NOT NULL DEFAULT 'waiting'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_id`, `user_id`, `product_id`, `product_color`, `price`, `quantity`, `ordered_date`, `delivered_date`, `delivery_address`, `product_size`, `status`) VALUES
(50, 26, 16, '', '19000', 2, '2020-06-27', '2020-06-27', 'Lamachaur,Pokhara', 0, 'completed'),
(51, 34, 2, '', '1840', 1, '2020-07-02T08:11:05.070', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'waiting'),
(53, 26, 6, 'blue', '750000', 3, '2020-07-02T13:17:35.905', 'Not delivered yet', 'Lamachaur,Pokhara', 5.9, 'waiting'),
(59, 26, 1, 'blue', '5130', 3, '2020-07-03T11:35:56.793', 'Not delivered yet', 'Lamachaur,Pokhara', 41, 'dispatched'),
(65, 26, 6, 'blue', '250000', 1, '2020-07-06T13:09:01.211', 'Not delivered yet', 'Lamachaur,Pokhara', 5.9, 'waiting'),
(66, 26, 7, '', '1710000', 3, '2020-07-06T13:15:26.593', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'waiting'),
(67, 26, 7, '', '1140000', 2, '2020-07-06T13:47:44.415', 'Not delivered yet', 'Miruwa,Pokhara', 0, 'waiting'),
(68, 26, 2, '', '3680', 2, '2020-07-06T14:09:14.515', 'Not delivered yet', 'New Road,Pokhara', 0, 'waiting'),
(69, 26, 6, 'blue', '1250000', 5, '2020-07-06T14:16:30.738', 'Not delivered yet', 'Lamachaur,Pokhara', 5.9, 'waiting'),
(70, 26, 10, '', '485000', 2, '2020-07-06T14:23:07.237', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'completed'),
(71, 26, 11, '', '5100', 3, '2020-07-06T14:25:09.502', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'waiting'),
(72, 26, 20, '', '5000', 1, 'Sun Mar 14 10:52:06 GMT+05:45 2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(73, 26, 1, '', '1800', 2, 'Sun Mar 14 10:52:06 GMT+05:45 2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(74, 26, 8, '', '50000', 1, 'Sun Mar 14 11:10:03 GMT+05:45 2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(75, 26, 6, '', '250000', 1, 'Sun Mar 14 11:10:03 GMT+05:45 2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(76, 26, 9, '', '195500', 1, 'Mon Mar 15 07:37:30 GMT+05:45 2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(77, 26, 9, '', '195500', 1, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(78, 26, 6, 'red', '250000', 1, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 6, 'Waiting'),
(79, 26, 8, '', '57500', 1, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(80, 26, 19, '', '1152', 1, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(81, 26, 16, '', '8476', 2, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(82, 26, 19, '', '-174', 1, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting'),
(83, 26, 19, '', '954', 2, '03-15-2021', 'Not delivered yet', 'Lamachaur,Pokhara', 0, 'Waiting');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `price` varchar(100) NOT NULL,
  `picture_path` varchar(255) NOT NULL,
  `category` varchar(100) NOT NULL,
  `brand` varchar(100) NOT NULL,
  `sku` varchar(30) NOT NULL,
  `type` varchar(255) NOT NULL,
  `discount` int(11) DEFAULT 0,
  `stock` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `colors` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '[]',
  `sizes` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '[]'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `product_name`, `description`, `price`, `picture_path`, `category`, `brand`, `sku`, `type`, `discount`, `stock`, `seller_id`, `colors`, `sizes`) VALUES
(1, 'Gold star shoe', 'classy gold star shoe for every foot', '1800', '5181682618561_IMG.jpg', 'Shoes', 'goldstar', '5181682618561', 'Mens Fashion', 5, 12, 27, '[\"red\",\"blue\"]', '[\"41\",\"42\"]'),
(2, 'Ray Ban Sunglasses for men', 'stylish and classy sunglasses', '2000', '51765171_IMG.jpg', 'Sunglasses', 'Ray Ban', '51765171', 'Mens Fashion', 8, 0, 28, '[]', '[]'),
(6, 'Iphone 11 Max', 'Iphone 11 Max 6inch 512gb ROM 16gb RAM oled retina display Snapdragon 1045', '250000', '41251511_IMG.jpg', 'Cell Phones And Accessories', 'Apple', '41251511', 'Electronics', 0, 0, 29, '[]', '[]'),
(7, 'Rolex watch', 'only for rich people', '600000', '42181181_IMG.jpg', 'Watches', 'Rolex', '42181181', 'Mens Fashion', 5, 0, 33, '[]', '[]'),
(8, 'Black Leather jacket', 'made from world\'s expensive leather material pashmina', '50000', '51816841_IMG.jpg', 'Jackets', 'leather', '51816841', 'Mens Fashion', 0, 0, 27, '[]', '[]'),
(9, 'LG B9 OLED', 'LG B9 OLED 32 4k wrgb hdr', '170000', '5221414_IMG.jpg', 'Television And Video', 'LG', '5221414', 'Electronics', 0, 0, 29, '[]', '[]'),
(10, 'Dell XPS 13', 'dell xps 13 qhd i7 10th gen 16ggb RAM 1tb SSD', '250000', '3261436131_IMG.jpg', 'Computers And Accessories', 'Dell', '3261436131', 'Electronics', 3, 0, 32, '[]', '[]'),
(11, 'Graffiti Bagpack BS', 'graffiti school bagpack for kids and adults', '2000', '234652_IMG.jpg', 'Bags', 'BS', '234652', 'Mens Fashion', 15, 0, 31, '[]', '[]'),
(12, 'martins jacket', 'nice jacket', '1500', '6415175171_IMG.jpg', 'Jackets', 'dr martin', '6415175171', 'Womens Fashion', 2, 0, 30, '[]', '[]'),
(13, 'The Last Of Us Part II', 'The Last of Us Part II is an action-adventure survival horror game played from the third-person perspective', '7000', '731816811_IMG.jpg', 'Games', 'playstation game', '731816811', 'Software', 3, 2, 33, '[]', '[]'),
(16, 'CyberPunk 2077', 'Cyberpunk is a subgenre of science fiction in a dystopian futuristic setting that tends to focus on a combination of low-life and high tech', '10000', '92827_IMG.jpg', 'Video Games And Consoles', 'cyber', '92827', 'Software', 5, 0, 27, '[]', '[]'),
(19, 'PUBG MOBILE Unknown Cash Direct Top Up (650 UC)', 'Only Player ID required for PUBG Mobile UC top-up.You may stay logged in throughout the transaction, once the top-up is completed, you will receive in your pubg mobile account', '1350', '16216_IMG.jpg', 'Video Games And Consoles', 'No brand', '16216', 'Software', 2, 0, 27, '[]', '[]'),
(20, 'KN95 Protective Mask (with Valve Filter)', 'Face mask with filter. Filters at least 95% of airborne particles (0.3 micron) particles.\nThin & light multi-layer design reducing respiratory resistance.\nSubstantial breathing area allows for sufficient airflow circulation without touching lips.\nHigh filtering ability, extremely low resistance, easy to breathe.', '5000', '12414_IMG.jpg', 'Others', 'No Brand', '12414', 'Mens Fashion', 58, 0, 27, '[]', '[]'),
(21, 'Converse Jack Purcell All Star OX Grey Shoes', 'Converse?s range of sneakers need no introduction as they are a global brand with almost cult-like following. Steeped in history, unique in design and reliable in function, Converse has never failed anyone. So, make your feet happy and skip to the beat of your life?s rhythm with this fun sneakers.', '3000', '1876184_IMG.jpg', 'shoes', 'converse', '1876184', 'Mens Fashion', 2, 20, 27, '[]', '[]'),
(24, 'Jeans Pant', 'jeans pant kers need no introduction unique in design and reliable in function, Converse has never failed anyone. So, make your feet happy and skip to the beat of', '200', '16184_img.jpg', '', 'converse', '16184', 'Mens Fashion', 2, 20, 27, '[\"green\"]', '[\"22\",\"23\"]'),
(25, 'Sexy Top For Women', 'sexy top for Nepalese women... ignore the taboo', '854', '109284_IMG.jpg', 'Shirts', 'Gucci', '109284', 'Womens Fashion', 5, 100, 27, '[\"pink\",\"White\"]', '[\"44\",\"42\"]');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `message` longtext NOT NULL,
  `rating` text NOT NULL,
  `date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `user_id`, `product_id`, `message`, `rating`, `date`) VALUES
(1, 34, 1, 'Decent enough product', '4.5', '2020-07-08'),
(2, 26, 1, 'good product', '2', '2020-07-08'),
(4, 26, 16, 'Very good product.i would give 5 star but bundle is missing!!!', '4.5', '2020-07-09'),
(7, 26, 10, 'good product', '3.5', '2020-07-09');

-- --------------------------------------------------------

--
-- Table structure for table `user_info`
--

CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `delivery_address` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'USER',
  `age` text NOT NULL,
  `gender` text NOT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_info`
--

INSERT INTO `user_info` (`user_id`, `user_name`, `password`, `phone`, `delivery_address`, `role`, `age`, `gender`, `latitude`, `longitude`) VALUES
(26, 'sagardevkota55@fb.com', '$2a$10$kzG7HpQGKgjd0YCvLZVsfOVa60tYw6KV8vN..Es64.7qfqSv6NhZ2', '9804111848', 'Lamachaur,Pokhara', 'USER', '22', 'male', 28.2612789, 83.9720581),
(27, 'goldstarShoes', '$2y$12$rJ.skVuLu2r3YV/WZ.Ja0.692orIhFEtX0DQbPFIdJuBF7FVh6ucK', '9819176580', 'malepatan,Pokhara', 'SELLER', '', '', NULL, NULL),
(28, 'opticalCenterNepal', '$2b$10$m16lKpM7qrWqrf0SjXRya.S5T5BOZQmfDMFs89cjlL9t1rF38/twG', '98121414141', NULL, 'SELLER', '', '', NULL, NULL),
(29, 'DajuBhaiMobilesAndAccessories', '$2y$12$Ym7ZDEoc5xVfJUukRRpfNuruQQqtBReQUy6axzXLTawPD/xEffO72', NULL, NULL, 'SELLER', '', '', NULL, NULL),
(30, 'latidoClothing', '$2y$12$QspMhtz1SVjJnObV0q9cOeZVT/f2ZmR/HwSh5wH1gQwOC8ovmiLyC', '9821841684', NULL, 'SELLER', '', '', NULL, NULL),
(31, 'usupsoBags', '$2y$12$gKrAbOzBujkWdF5lwYn/ceJp3ID776D9W1xWjRqHS7eFa23Xb58m2', NULL, NULL, 'SELLER', '', '', NULL, NULL),
(32, 'NoteBookFever', '$2y$12$zxpPCgXL1mVNOyQTAeabtexP.HciKPtInAyNjy5WDuVYEqAjECYxW', NULL, NULL, 'SELLER', '', '', NULL, NULL),
(33, 'BestChoice', '$2y$12$hZIqcp3FcRNDB6mUxMhE3e7uT44h69vIgJyaOQeSC3JouIhMJpN5K', NULL, NULL, 'SELLER', '', '', NULL, NULL),
(34, 'sagardevkota55@yahoo.com', '$2a$10$.YOCOTGNrvp3MEcaiDFPQOWrirINl6Gf9al7SBKpVX4xmvgcjaQDG', '9846710086', 'Miruwa,Pokhara', 'ADMIN', '', '', 28.2408085, 83.9838862);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `carts`
--
ALTER TABLE `carts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `conversations`
--
ALTER TABLE `conversations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `coupons`
--
ALTER TABLE `coupons`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`),
  ADD UNIQUE KEY `sku` (`sku`),
  ADD KEY `seller_id` (`seller_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `user_info`
--
ALTER TABLE `user_info`
  ADD PRIMARY KEY (`user_id`,`user_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `carts`
--
ALTER TABLE `carts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=263;

--
-- AUTO_INCREMENT for table `conversations`
--
ALTER TABLE `conversations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `coupons`
--
ALTER TABLE `coupons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=84;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user_info`
--
ALTER TABLE `user_info`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `carts`
--
ALTER TABLE `carts`
  ADD CONSTRAINT `carts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  ADD CONSTRAINT `carts_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `conversations`
--
ALTER TABLE `conversations`
  ADD CONSTRAINT `conversations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  ADD CONSTRAINT `conversations_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `coupons`
--
ALTER TABLE `coupons`
  ADD CONSTRAINT `coupons_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

--
-- Constraints for table `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user_info` (`user_id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`),
  ADD CONSTRAINT `reviews_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
