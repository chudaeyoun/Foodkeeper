/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
//var fcmToken = 0;
var fcmToken_x = "토큰값 : "
var app = {
    // Application Constructor
    initialize: function() {
        document.addEventListener('deviceready', this.onDeviceReady.bind(this), false);
    },

    // deviceready Event Handler
    //
    // Bind any cordova events here. Common events are:
    // 'pause', 'resume', etc.
    onDeviceReady: function() {
        this.receivedEvent('deviceready');
		FCMPlugin.getToken(
			  function(token){
				console.log("token :"+ token)
				alert("token : "+ token);
				fcmToken = token;
			  },
			  function(err){
				console.log('error retrieving token: ' + err);
			  }
	    );
		FCMPlugin.onNotification(
              
            function (data) {
                
                if (data.wasTapped) {
                   
                    console.log("Push Notification tapped", data);
                } else {
                   
                    // this will be the state when app is already opened.
                    console.log("Push Notification", data);

                }

            },
            function (msg) {
                console.log('onNotification callback successfully registered: ' + msg);

            },
            function (err) {
                console.log('Error registering onNotification callback: ' + err);
            }
        );
		/*
		FCMPlugin.getToken(function(token){
            //window.localStorage.setItem("token", token); 
            //console.log("TOKEN FIREBASE : " + token);
			alert("OUR TOKEN : " + token);
			fcmToken = token;
        }, function (error) {
            console.error(error);
        });*/
		
		
    },

    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }
};

app.initialize();