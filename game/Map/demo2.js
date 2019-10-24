(function(name,data){
 if(typeof onTileMapLoaded === 'undefined') {
  if(typeof TileMaps === 'undefined') TileMaps = {};
  TileMaps[name] = data;
 } else {
  onTileMapLoaded(name,data);
 }
 if(typeof module === 'object' && module && module.exports) {
  module.exports = data;
 }})("demo2",
{ "height":7,
 "infinite":false,
 "layers":[
        {
         "data":[25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25],
         "height":7,
         "id":4,
         "name":"background",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 48, 48, 48, 48, 48, 5, 0, 0, 0, 0, 0, 0, 26, 1, 2, 2, 2, 3, 24, 0, 0, 0, 0, 0, 0, 26, 24, 0, 0, 0, 26, 24, 0, 4, 48, 48, 48, 0, 26, 24, 0, 0, 0, 26, 47, 48, 49, 1, 2, 2, 0, 26, 24, 0, 0, 0, 27, 2, 2, 2, 28, 0, 0, 0, 26, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         "height":7,
         "id":1,
         "name":"road",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }, 
        {
         "data":[136, 0, 132, 0, 0, 136, 0, 0, 0, 0, 135, 131, 137, 133, 136, 0, 0, 0, 0, 0, 135, 0, 0, 0, 136, 135, 131, 0, 0, 0, 0, 0, 0, 0, 0, 133, 0, 0, 0, 135, 0, 0, 133, 131, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 0, 0, 0, 0, 0, 136, 0, 0, 131, 0, 0, 133, 0, 0, 0, 133, 137, 0, 0, 0, 0, 135, 0, 138, 0, 0, 132, 0, 0, 131, 136],
         "height":7,
         "id":2,
         "name":"tree_rock",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 16, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 0, 0, 16, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 16, 0, 0, 0],
         "height":7,
         "id":3,
         "name":"spawn",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }],
 "nextlayerid":5,
 "nextobjectid":1,
 "orientation":"orthogonal",
 "renderorder":"right-down",
 "tiledversion":"1.2.5",
 "tileheight":64,
 "tilesets":[
        {
         "firstgid":1,
         "source":"towerDefenseDemo.tsx"
        }],
 "tilewidth":64,
 "type":"map",
 "version":1.2,
 "width":13
});