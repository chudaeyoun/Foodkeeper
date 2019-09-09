<?php
$randomNum = mt_rand(1, 7);

if($randomNum == 1)
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":1, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2019-09-01\",\"name\":\"페레로로쉐\", \"price\": 3000, \"barcode\":\"80050278\", \"expiredAt\":\"2019.09.02\"}}";
else if($randomNum == 2)
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":2, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2019-09-01\",\"name\":\"요구르트\", \"price\": 600, \"barcode\":\"801056836013\", \"expiredAt\":\"2019.09.02\"}}";
else if($randomNum == 3)
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":3, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2100-09-01\",\"name\":\"서울우유 350ml\", \"price\": 1000, \"barcode\":\"801056836015\", \"expiredAt\":\"2050.09.02\"}}";
else if($randomNum == 4)
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":4, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2500-09-01\",\"name\":\"스파클\", \"price\": 800, \"barcode\":\"801056836020\", \"expiredAt\":\"2019.09.02\"}}";
else if($randomNum == 5)
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":5, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2400-09-01\",\"name\":\"잔치집 식혜(롯데)\", \"price\": 1000, \"barcode\":\"8801056063245\", \"expiredAt\":\"2060.09.02\"}}";
else if($randomNum == 6)
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":6, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2019-09-01\",\"name\":\"크런키\", \"price\": 1500, \"barcode\":\"8801062628476\", \"expiredAt\":\"2080.09.02\"}}";
else
	echo " {\"result\" : \"SUCCESS\", \"data\" : {\"id\":7, \"createdTime\":\"2019.09.01\", \"updatedTime\":\"2019-09-01\",\"name\":\"꿀홍삼\", \"price\": 1000000, \"barcode\":\"8801382127130\", \"expiredAt\":\"2100.09.02\"}}";

?>