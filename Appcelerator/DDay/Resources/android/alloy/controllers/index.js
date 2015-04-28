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
        var actionBar = $.mainWWindow.activity.actionBar;
        console.log("ActionBar exists and will define properties...!");
        actionBar.title = "Dream Destination a Day";
        $.mainWWindow.activity.onCreateOptionsMenu = function(e) {
            var menu = e.menu;
            var reload = menu.add({
                title: "Reload",
                showAsAction: Ti.Android.SHOW_AS_ACTION_NEVER
            });
            reload.addEventListener("click", function() {
                getRemoteData();
            });
            var share = menu.add({
                title: "Share",
                showAsAction: Ti.Android.SHOW_AS_ACTION_NEVER
            });
            share.addEventListener("click", function() {
                sharePOI();
            });
        };
    }
    function getRemoteData() {
        var url = defaultURL + "ddday";
        var client = Ti.Network.createHTTPClient({
            onload: function() {
                Ti.API.info("Received = " + this.responseText);
                var poi = JSON.parse(this.responseText);
                $.destinatonImage.image = poi.POIimageURL;
                $.destinationName.text = poi.POIname;
                $.destinationPlace.title = poi.POIdetails;
                Ti.App.Properties.setObject("poi", poi);
            },
            onerror: function(e) {
                alert("Error on communication " + e.toString);
            },
            timeout: 5e3
        });
        client.open("GET", url);
        client.send();
    }
    function openMap() {
        var mapWindow = Alloy.createController("destinationMap").getView();
        mapWindow.open({
            modalTransitionStyle: Titanium.UI.iPhone.MODAL_TRANSITION_STYLE_COVER_VERTICAL
        });
    }
    function sharePOI() {
        var shareDestWindow = Alloy.createController("shareDestination").getView();
        shareDestWindow.open({
            modalTransitionStyle: Titanium.UI.iPhone.MODAL_TRANSITION_STYLE_COVER_VERTICAL
        });
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "index";
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
    $.__views.mainWWindow = Ti.UI.createWindow({
        backgroundColor: "white",
        id: "mainWWindow",
        title: "Dream Destination a Day",
        fullscreen: "false"
    });
    $.__views.mainWWindow && $.addTopLevelView($.__views.mainWWindow);
    $.__views.__alloyId5 = Ti.UI.createButton({
        width: Ti.UI.SIZE,
        height: Ti.UI.SIZE,
        image: "share.png",
        id: "__alloyId5"
    });
    sharePOI ? $.__views.__alloyId5.addEventListener("click", sharePOI) : __defers["$.__views.__alloyId5!click!sharePOI"] = true;
    $.__views.mainWWindow.leftNavButton = $.__views.__alloyId5;
    $.__views.__alloyId7 = Ti.UI.createButton({
        width: Ti.UI.SIZE,
        height: Ti.UI.SIZE,
        image: "update.png",
        id: "__alloyId7"
    });
    getRemoteData ? $.__views.__alloyId7.addEventListener("click", getRemoteData) : __defers["$.__views.__alloyId7!click!getRemoteData"] = true;
    $.__views.mainWWindow.rightNavButton = $.__views.__alloyId7;
    $.__views.destinatonImage = Ti.UI.createImageView({
        id: "destinatonImage",
        top: "0",
        width: Ti.UI.FILL,
        height: Ti.UI.FILL
    });
    $.__views.mainWWindow.add($.__views.destinatonImage);
    openMap ? $.__views.destinatonImage.addEventListener("click", openMap) : __defers["$.__views.destinatonImage!click!openMap"] = true;
    $.__views.__alloyId8 = Ti.UI.createView({
        bottom: "0",
        backgroundColor: "#ffffff",
        height: "60",
        id: "__alloyId8"
    });
    $.__views.mainWWindow.add($.__views.__alloyId8);
    $.__views.destinationName = Ti.UI.createLabel({
        width: Ti.UI.SIZE,
        height: Ti.UI.SIZE,
        color: "#000",
        id: "destinationName",
        text: "",
        top: "5"
    });
    $.__views.__alloyId8.add($.__views.destinationName);
    $.__views.destinationPlace = Ti.UI.createButton({
        width: Ti.UI.FILL,
        height: Ti.UI.SIZE,
        id: "destinationPlace",
        title: "",
        top: "20"
    });
    $.__views.__alloyId8.add($.__views.destinationPlace);
    openMap ? $.__views.destinationPlace.addEventListener("click", openMap) : __defers["$.__views.destinationPlace!click!openMap"] = true;
    exports.destroy = function() {};
    _.extend($, $.__views);
    $.mainWWindow.addEventListener("open", function() {
        createActionBar();
        getRemoteData();
    });
    $.mainWWindow.open();
    __defers["$.__views.__alloyId5!click!sharePOI"] && $.__views.__alloyId5.addEventListener("click", sharePOI);
    __defers["$.__views.__alloyId7!click!getRemoteData"] && $.__views.__alloyId7.addEventListener("click", getRemoteData);
    __defers["$.__views.destinatonImage!click!openMap"] && $.__views.destinatonImage.addEventListener("click", openMap);
    __defers["$.__views.destinationPlace!click!openMap"] && $.__views.destinationPlace.addEventListener("click", openMap);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;