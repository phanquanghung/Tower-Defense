(function(name,data){
 if(typeof onTileMapLoaded === 'undefined') {
  if(typeof TileMaps === 'undefined') TileMaps = {};
  TileMaps[name] = data;
 } else {
  onTileMapLoaded(name,data);
 }
 if(typeof module === 'object' && module && module.exports) {
  module.exports = data;
 }})("demo3",
{ "height":7,
 "infinite":false,
 "layers":[
        {
         "data":[25, 25, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 35, 25, 25, 25, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 25, 25, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 35, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 35, 35, 35, 25, 25, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 35, 25, 25, 25, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 25, 25, 25, 25, 25, 25, 25, 35, 35, 35, 35, 35, 35],
         "height":7,
         "id":1,
         "name":"background",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 36, 34, 0, 0, 4, 48, 48, 48, 5, 0, 0, 0, 0, 36, 34, 0, 0, 26, 1, 2, 3, 24, 0, 0, 0, 0, 36, 34, 0, 0, 26, 24, 0, 26, 24, 0, 0, 0, 0, 36, 34, 0, 0, 26, 24, 0, 26, 47, 48, 48, 48, 58, 59, 34, 0, 0, 26, 24, 0, 27, 2, 2, 2, 2, 12, 12, 38, 0, 0, 26, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
         "height":7,
         "id":2,
         "name":"road",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 131, 0, 0, 133, 0, 0, 132, 0, 138, 0, 0, 138, 0, 0, 0, 0, 0, 0, 133, 0, 133, 0, 0, 0, 0, 133, 0, 0, 0, 0, 0, 0, 0, 137, 0, 0, 0, 136, 0, 0, 0, 132, 0, 0, 138, 0, 0, 138, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 135, 0, 0, 0, 135, 0, 0, 0, 0, 0, 0, 0, 137, 132, 0, 0, 133, 0, 0, 132, 136, 0, 0, 137, 0, 0],
         "height":7,
         "id":3,
         "name":"tree_rock",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":13,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 16, 0, 16, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 16, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 16, 0, 0, 0, 0],
         "height":7,
         "id":4,
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