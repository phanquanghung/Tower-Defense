(function(name,data){
 if(typeof onTileMapLoaded === 'undefined') {
  if(typeof TileMaps === 'undefined') TileMaps = {};
  TileMaps[name] = data;
 } else {
  onTileMapLoaded(name,data);
 }
 if(typeof module === 'object' && module && module.exports) {
  module.exports = data;
 }})("level1",
{ "height":10,
 "infinite":false,
 "layers":[
        {
         "data":[25, 25, 25, 25, 25, 25, 25, 25, 25, 94, 94, 94, 94, 94, 94, 94, 25, 25, 25, 25, 25, 25, 25, 25, 25, 94, 94, 94, 94, 94, 94, 94, 25, 25, 25, 25, 25, 25, 25, 25, 25, 94, 94, 94, 94, 94, 94, 94, 25, 25, 25, 25, 25, 25, 25, 25, 25, 94, 94, 94, 94, 94, 94, 94, 25, 25, 25, 25, 25, 25, 25, 25, 242, 242, 242, 242, 242, 242, 242, 242, 25, 25, 25, 25, 25, 25, 25, 25, 242, 242, 242, 242, 242, 242, 242, 242, 25, 25, 25, 25, 25, 25, 25, 25, 242, 242, 242, 242, 242, 242, 242, 242, 25, 25, 25, 25, 25, 25, 25, 25, 242, 242, 242, 242, 242, 242, 242, 242, 25, 25, 25, 25, 25, 25, 25, 25, 242, 242, 242, 242, 242, 242, 242, 242, 25, 25, 25, 25, 25, 25, 25, 25, 242, 242, 242, 242, 242, 242, 242, 242],
         "height":10,
         "id":1,
         "name":"background",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":16,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 73, 117, 117, 117, 74, 0, 0, 0, 4, 48, 48, 48, 48, 5, 0, 0, 95, 70, 71, 72, 93, 0, 0, 0, 26, 1, 2, 2, 3, 24, 0, 0, 95, 93, 0, 95, 93, 0, 0, 0, 26, 24, 0, 0, 26, 24, 0, 0, 243, 241, 0, 243, 241, 0, 0, 0, 26, 24, 0, 0, 26, 24, 0, 0, 243, 241, 0, 243, 241, 0, 48, 48, 49, 24, 0, 0, 26, 47, 265, 265, 266, 241, 0, 243, 241, 0, 2, 2, 2, 28, 0, 0, 27, 2, 219, 219, 219, 245, 0, 243, 241, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 243, 241, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 243, 241, 0],
         "height":10,
         "id":2,
         "name":"road",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":16,
         "x":0,
         "y":0
        }, 
        {
         "data":[131, 132, 0, 136, 135, 0, 0, 21, 133, 0, 0, 66, 132, 66, 0, 0, 0, 136, 0, 43, 0, 21, 43, 0, 0, 131, 22, 0, 0, 0, 20, 135, 132, 0, 133, 0, 0, 0, 0, 0, 132, 0, 0, 0, 0, 0, 0, 133, 135, 136, 0, 22, 0, 0, 0, 0, 0, 66, 0, 0, 66, 0, 0, 66, 0, 43, 0, 0, 43, 133, 0, 0, 22, 0, 0, 0, 138, 0, 0, 138, 131, 0, 0, 0, 132, 0, 21, 0, 0, 0, 22, 136, 0, 0, 22, 0, 0, 22, 0, 0, 43, 133, 0, 0, 0, 0, 0, 21, 137, 0, 0, 112, 0, 0, 0, 135, 0, 0, 0, 21, 0, 22, 0, 0, 0, 0, 0, 136, 132, 21, 43, 0, 133, 0, 0, 43, 0, 0, 112, 0, 20, 0, 0, 20, 133, 0, 0, 132, 0, 0, 0, 0, 0, 0, 138, 0, 112, 20, 0, 137],
         "height":10,
         "id":3,
         "name":"tree_rock",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":16,
         "x":0,
         "y":0
        }, 
        {
         "data":[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 16, 0, 0, 0, 0, 0, 16, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 16, 0, 0, 16, 0, 16, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 16, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0],
         "height":10,
         "id":4,
         "name":"spawn",
         "opacity":1,
         "type":"tilelayer",
         "visible":true,
         "width":16,
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
 "width":16
});