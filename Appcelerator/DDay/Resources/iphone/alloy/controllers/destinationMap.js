function __processArg(obj, key) {
    var arg = null;
    if (obj) {
        arg = obj[key] || null;
        delete obj[key];
    }
    return arg;
}

function Controller() {
    function sharePOI() {
        var shareDestWindow = Alloy.createController("shareDestination").getView();
        shareDestWindow.open({
            modalTransitionStyle: Titanium.UI.iPhone.MODAL_TRANSITION_STYLE_COVER_VERTICAL
        });
    }
    function closeDestMap() {
        $.destMapNWindow.close();
    }
    require("alloy/controllers/BaseController").apply(this, Array.prototype.slice.call(arguments));
    this.__controllerPath = "destinationMap";
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
    $.__views.destMapWWindow = Ti.UI.createWindow({
        backgroundColor: "white",
        id: "destMapWWindow",
        title: "Dream Destination a Day",
        fullscreen: "false"
    });
    $.__views.__alloyId1 = Ti.UI.createButton({
        image: "back.png",
        id: "__alloyId1"
    });
    closeDestMap ? $.__views.__alloyId1.addEventListener("click", closeDestMap) : __defers["$.__views.__alloyId1!click!closeDestMap"] = true;
    $.__views.destMapWWindow.leftNavButton = $.__views.__alloyId1;
    $.__views.__alloyId3 = Ti.UI.createButton({
        image: "share.png",
        id: "__alloyId3"
    });
    sharePOI ? $.__views.__alloyId3.addEventListener("click", sharePOI) : __defers["$.__views.__alloyId3!click!sharePOI"] = true;
    $.__views.destMapWWindow.rightNavButton = $.__views.__alloyId3;
    $.__views.mapview = Alloy.Globals.Map.createView({
        id: "mapview",
        animate: "true",
        regionFit: "true",
        userLocation: "true",
        height: Ti.UI.FILL,
        top: "0",
        left: "0"
    });
    $.__views.destMapWWindow.add($.__views.mapview);
    $.__views.destMapNWindow = Ti.UI.iOS.createNavigationWindow({
        window: $.__views.destMapWWindow,
        id: "destMapNWindow"
    });
    $.__views.destMapNWindow && $.addTopLevelView($.__views.destMapNWindow);
    exports.destroy = function() {};
    _.extend($, $.__views);
    $.destMapNWindow.addEventListener("open", function() {
        var poi = Ti.App.Properties.getObject("poi");
        $.mapview.setLocation({
            latitude: poi.POIlat,
            longitude: poi.POIlng,
            latitudeDelta: .15,
            longitudeDelta: .15
        });
        var annotation;
        var annotation = Alloy.Globals.Map.createAnnotation({
            latitude: poi.POIlat,
            longitude: poi.POIlng,
            title: poi.POIname,
            subtitle: poi.POIdetails,
            pincolor: Alloy.Globals.Map.ANNOTATION_RED,
            animate: true,
            leftView: Ti.UI.createImageView({
                image: poi.POIimageURL,
                height: 50,
                width: 50
            }),
            rightButton: Ti.UI.iPhone.SystemButton.INFO_DARK,
            placeId: poi.POIid
        });
        $.mapview.addAnnotation(annotation);
    });
    $.mapview.addEventListener("click", function(e) {
        console.log("Click Source -> " + e.clicksource);
        if ("rightButton" == e.clicksource || "title" == e.clicksource || "subtitle" == e.clicksource) {
            console.log("ID -> " + e.annotation.placeId);
            console.log("Name -> " + e.annotation.title);
            var shareDestWindow = Alloy.createController("shareDestination").getView();
            shareDestWindow.open({
                modalTransitionStyle: Titanium.UI.iPhone.MODAL_TRANSITION_STYLE_COVER_VERTICAL
            });
        }
    });
    __defers["$.__views.__alloyId1!click!closeDestMap"] && $.__views.__alloyId1.addEventListener("click", closeDestMap);
    __defers["$.__views.__alloyId3!click!sharePOI"] && $.__views.__alloyId3.addEventListener("click", sharePOI);
    _.extend($, exports);
}

var Alloy = require("alloy"), Backbone = Alloy.Backbone, _ = Alloy._;

module.exports = Controller;