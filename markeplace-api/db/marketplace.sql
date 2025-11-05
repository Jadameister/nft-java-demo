
CREATE DATABASE `marketplace`;

USE `marketplace`;

DROP TABLE IF EXISTS `items`;

CREATE TABLE `items`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `itemid` varchar(28)  NOT NULL,
    `userid` int(10) unsigned NOT NULL,
    `editionnum` int(10) unsigned NOT NULL,
    `tokenUri`  varchar(500) NOT NULL,
    `price` varchar(32) NOT NULL,
    `listingPrice` varchar(32) NOT NULL,
    `seller` varchar(50) NOT NULL,
    `owner` varchar(50) NOT NULL,
    `image` varchar(100) NOT NULL,
    `itemname` varchar(100) NOT NULL,
     `creatername` varchar(100) NOT NULL,
    `description` varchar(500) NOT NULL,
    `hashtag` varchar(25) NOT NULL,
    `percentage` varchar(10)  NOT NULL,
    `releasedate` varchar(32) NOT NULL,
     UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `collectionid` varchar(32) NOT NULL,
    `timestamp` varchar(32) NOT NULL,
    `collectionname`  varchar(32) NOT NULL,
    `userid` int(32) NOT NULL,
    `ownername` varchar(32) NOT NULL,
    `itemids` varchar(28) NOT NULL,
     UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `collections`;

CREATE TABLE `collections`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `timestamp` varchar(32) NOT NULL,
     `collectionsname` varchar(32) NOT NULL,
    `userid` int(10) unsigned NOT NULL,
    `collectionid` varchar(32) NOT NULL,
     UNIQUE KEY `id` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `MarketItem`;

CREATE TABLE `MarketItem`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `itemid` varchar(40)  NOT NULL,
    `nftContract`  varchar(32) NOT NULL,
    `tokenId`   varchar(32) NOT NULL,
    `seller`  varchar(32) NOT NULL,
    `owner`  varchar(32) NOT NULL,
    `price`  varchar(32) NOT NULL,
    `sold`  TINYINT,
     UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;