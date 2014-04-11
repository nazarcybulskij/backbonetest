<html>
<script src="http://code.jquery.com/jquery-1.10.2.min.js" type="text/javascript" ></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#test").click(function(){
            $.get("/test",function(data,status){
                alert("Data: " + data + "\nStatus: " + status);
            });
        });
    });
</script>



<script  src="js/jquery-1.11.0.js"></script>
<script  src="js/underscore.js"></script>
<script  src="js/backbone.js"></script>
<script  src="js/backbone-validation.js"></script>
<script  src="js/main.js"></script>

<body>
<button id="test">Load</button>

</body>
</html>