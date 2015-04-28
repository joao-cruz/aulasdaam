function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function createActionBar() {
        console.log("Creating the ActionBar");
        var actionBar = $.shareDestNWindow.activity.actionBar;
        console.log("ActionBar exists and will define properties...!");
        actionBar.title = "Dream Destination a Day";
        actionBar.setDisplayHomeAsUp(true);
        actionBar.onHomeIconItemSelected = function() {
            $.shareDestNWindow.close();
        };
    }
    function getUserInfo() {
        facebook.requestWithGraphPath("me", {}, "GET", function(e) {
            if (e.success) {
                var result = JSON.parse(e.result);
                Ti.API.info("Result = " + e.result);
                Ti.App.Properties.setString("facebookUid", facebook.uid);
                Ti.API.info("facebookUid = " + facebook.uid);
                Ti.App.Properties.setString("facebookUsername", result.username);
                Ti.API.info("facebookUsername = " + result.username);
                Ti.App.Properties.setString("facebookName", result.first_name + " " + result.last_name);
                Ti.API.info("facebookName = " + result.first_name + " " + result.last_name);
                Ti.App.Properties.setString("facebookEmail", result.email);
                Ti.API.info("facebookEmail = " + result.email);
                Ti.App.Properties.setString("facebookToken", facebook.accessToken);
                Ti.API.info("facebookToken = " + facebook.accessToken);
            } else alert("Some error has occured!!!");
        });
    }
    function shareDestinationFB() {
        var poi = Ti.App.Properties.getObject("poi");
        var data = {
            name: poi.POIname,
            message: $.destinationShareText.value,
            caption: $.destinationShareText.value,
            picture: poi.POIimageURL,
            description: $.destinationShareText.value
        };
        facebook.dialog("feed", data, function(e) {
            if (e.success && e.result) {
                alert("Success! New Post ID: " + e.result);
                shareDestinationAPIREST();
                $.shareDestNWindow.close();
            } else alert(e.error ? e.error : "User canceled dialog.");
        });
    }
    function shareDestinationAPIREST() {
        var poi = Ti.App.Properties.getObject("poi");
        var url = defaultURL + "ddday";
        var now = new Date();
        var data = {
            UUID: Ti.App.Properties.getString("facebookUid"),
            email: Ti.App.Properties.getString("facebookEmail"),
            FB: true,
            TW: false,
            POIid: poi.POIid,
            date: now
        };
        var client = Ti.Network.createHTTPClient({
            onload: function() {},
            onerror: function(e) {
                alert(e.error);
            },
            timeout: 5e3
        });
        client.open("POST", url);
        client.send(data);
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "shareDestination";
    if (arguments[0]) {
        {
            __processArg(arguments[0], "__parentSymbol");
        }
        {
            __processArg(arguments[0], "$model");
        }
        {
            __processArg(arguments[0], "__itemTemplate");
        }
    }
    var $ = this;
    var exports = {};
    var __defers = {};
    $.__views.shareDestNWindow = Ti.UI.createWindow({
        backgroundColor: "white",
        id: "shareDestNWindow",
        title: "Dream Destination a Day",
        fullscreen: "false"
    });
    $.__views.shareDestNWindow && $.addTopLevelView($.__views.shareDestNWindow);
    $.__views.destinationName = Ti.UI.createLabel({
        top: "2",
        id: "destinationName",
        text: ""
    });
    $.__views.shareDestNWindow.add($.__views.destinationName);
    $.__views.destinationImage = Ti.UI.createImageView({
        top: "30",
        id: "destinationImage",
        width: "50%",
        height: "50%"
    });
    $.__views.shareDestNWindow.add($.__views.destinationImage);
    $.__views.destinationShareText = Ti.UI.createTextArea({
        width: "90%",
        height: "30%",
        id: "destinationShareText",
        bottom: "60",
        borderColor: "#cccccc",
        borderWidth: "1",
        borderRadius: "10"
    });
    $.__views.shareDestNWindow.add($.__views.destinationShareText);
    $.__views.shareBtn = Ti.UI.createButton({
        id: "shareBtn",
        title: "Share",
        bottom: "20"
    });
    $.__views.shareDestNWindow.add($.__views.shareBtn);
    shareDestinationFB ? $.__views.shareBtn.addEventListener("click", shareDestinationFB) : __defers["$.__views.shareBtn!click!shareDestinationFB"] = true;
    exports.destroy = function() {};
    _.extend($, $.__views);
    var facebook = Alloy.Globals.Facebook;
    facebook.appid = 0x4e53b4a7f2b43;
    facebook.permissions = [ "user_about_me", "email", "user_photos", "publish_stream" ];
    facebook.forceDialogAuth = true;
    var defaultText = "My dream destination for today is ";
    $.shareDestNWindow.addEventListener("open", function() {
        createActionBar();
        facebook.authorize();
        var poi = Ti.App.Properties.getObject("poi");
        $.destinationImage.image = poi.POIimageURL;
        $.destinationName.text = poi.POIname;
        $.destinationShareText.value = defaultText + poi.POIname + " (" + poi.POIdetails + ") #ddday";
        facebook.loggedIn && getUserInfo();
    });
    facebook.addEventListener("login", function(e) {
        if (e.success) {
            alert("Facebook authentication with success!!!");
            getUserInfo();
        }
    });
    __defers["$.__views.shareBtn!click!shareDestinationFB"] && $.__views.shareBtn.addEventListener("click", shareDestinationFB);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;