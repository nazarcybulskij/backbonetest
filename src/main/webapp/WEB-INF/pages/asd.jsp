<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.4.2/pure-min.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.css">
    <link rel="stylesheet" type="text/css" href="css/demo.css">
</head>
<body>



<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script  src="http://underscorejs.org/underscore-min.js"></script>
<script  src="http://backbonejs.org/backbone-min.js"></script>
<script  src="js/jquery.gridster.js"></script>
<script  src="js/main.js"></script>



<script type="text/template" id="my_first_tamplate">
    <div>
        <strong><@= title  @></strong>
        <input type="button" id="edit" value='редагування'>
    </div>
</script>



<div>
    <input type="text" id="input_qeary" value="google" text="google">
    <button type="button" id="id_button"> Click Me :) </button>
</div>



<div id="fotka" class="gridster ready">

    <!--<ul>
          <li class="gs-w" data-row="2" data-col="1" data-sizex="2" data-sizey="4">
              <div style="float: left;">
                  <img src="http://cs314228.vk.me/v314228106/6065/nqI06XQJJXM.jpg"  alt="2013 Toyota Tacoma"
                          height="200" width="200"/>
              </div>
              <div>
                  <p>Toyota Tacoma</p>
              </div>
              <div style="float: none; clear: both;"></div>

          </li>
          <li class="gs-w" data-row="2" data-col="1" data-sizex="2" data-sizey="4"></li>
          <li class="gs-w" data-row="2" data-col="1" data-sizex="2" data-sizey="4"></li>
          <li class="gs-w" data-row="2" data-col="1" data-sizex="2" data-sizey="4"></li>

      </ul>      -->

</div>

<script type="text/javascript">
    var gridster;

    $(function(){

        gridster = $(".gridster ul").gridster({
            widget_base_dimensions: [100, 55],
            widget_margins: [5, 5],
            autogrow_cols: true,
            resize: {
                enabled: true
            }
        }).data('gridster');


    });

</script>
</body>
</html>