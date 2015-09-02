/**
 * Created by pmsg863 on 14-6-1.
 */
var RE = 6356755;
var Pai = 3.14159265358979324;
var EE = 0.00669342162296594323;

/// 地球坐标系(WGS-84)到百度坐标系的转换算法
function WGS2BD(wgpoint ,newpoint) {
    var dbpoint = WGS2GCJ(wgpoint,newpoint);
    GCJ2BD(dbpoint,newpoint);
    return newpoint;
}

/// 地球坐标系(WGS-84)到火星坐标系(GCJ-02)的转换算法
function WGS2GCJ(wgpoint,newpoint) {
    var mgpoint = newpoint;

    if (wgpoint.lng < 72.004 || wgpoint.lng > 137.8347 || wgpoint.lat < 0.8293 || wgpoint.lat > 55.8271) {
        wgpointmgpoint.lat = wgpoint.lat;
        mgpoint.lng = wgpoint.lng;
        return mgpoint;
    }

    var lat = TransformLat(wgpoint.lng - 105.0, wgpoint.lat - 35.0);
    var lon = TransformLon(wgpoint.lng - 105.0, wgpoint.lat - 35.0);

    var radLat = wgpoint.lat / 180.0 * Pai;

    var magic = Math.sin(radLat);
    magic = 1 - EE * magic * magic;

    var sqrtMagic = Math.sqrt(magic);

    lat = (lat * 180.0) / ((RE * (1 - EE)) / (magic * sqrtMagic) * Pai);
    lon = (lon * 180.0) / (RE / sqrtMagic * Math.cos(radLat) * Pai);

    mgpoint.lat = wgpoint.lat + lat;
    mgpoint.lng = wgpoint.lng + lon;

    return  mgpoint;
}

/// <summary>
/// 火星坐标系(GCJ-02)到百度坐标系的转换算法
function GCJ2BD(mgpoint,newpoint) {
    var bdpoint = newpoint;

    var x = mgpoint.lng;
    var y = mgpoint.lat;

    var z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * RE * 3000.0 / 180.0);
    var theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * RE * 3000.0 / 180.0);

    bdpoint.lng = z * Math.cos(theta) + 0.0065;
    bdpoint.lat = z * Math.sin(theta) + 0.006;
    return bdpoint;
}

function TransformLat(x, y) {
    var ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));

    ret += (20.0 * Math.sin(6.0 * x * Pai) + 20.0 * Math.sin(2.0 * x * Pai)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(y * Pai) + 40.0 * Math.sin(y / 3.0 * Pai)) * 2.0 / 3.0;
    ret += (160.0 * Math.sin(y / 12.0 * Pai) + 320 * Math.sin(y * Pai / 30.0)) * 2.0 / 3.0;

    return ret;
}

function TransformLon(x, y) {
    var ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));

    ret += (20.0 * Math.sin(6.0 * x * Pai) + 20.0 * Math.sin(2.0 * x * Pai)) * 2.0 / 3.0;
    ret += (20.0 * Math.sin(x * Pai) + 40.0 * Math.sin(x / 3.0 * Pai)) * 2.0 / 3.0;
    ret += (150.0 * Math.sin(x / 12.0 * Pai) + 300.0 * Math.sin(x / 30.0 * Pai)) * 2.0 / 3.0;

    return ret;
}
