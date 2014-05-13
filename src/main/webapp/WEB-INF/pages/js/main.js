$(function(){

    window.App={
        Models:{},
        Views:{},
        Collection:{}
    };

    Backbone.emulateHTTP=true;
    Backbone.emulateJSON=true;



    _.templateSettings = {
        interpolate: /\<\@\=(.+?)\@\>/gim,
        evaluate: /\<\@(.+?)\@\>/gim,
        escape: /\<\@\-(.+?)\@\>/gim
    };

    App.Models.Image=Backbone.Model.extend({
        default:{
            title:"",
            uri:""
        }
    });

    App.Views.Image_View=Backbone.View.extend({
        initialize:function(){
            this.model.on('change',this.render,this);
            //this.model.on('add',this.render,this);
        },
        tagName:'li',
        template: _.template($('#image_template').html()),

        render:function(){
            var template=this.template(this.model.toJSON());
            console.log(template);
            this.$el.html(template);
            $(".myphotos").glisse({speed: 500, changeSpeed: 550, effect:'roll', fullscreen: false});
            return this;
        }

    });

    App.Collection.Images=Backbone.Collection.extend({
        model:App.Models.Image,
        query:"Google",
        url:function(){
            return  "http://backnonetest-zhybulskij.rhcloud.com/test"+"?query=" + encodeURIComponent(this.query);
            //return  "http://localhost:8080/test"+"?query=" + encodeURIComponent(this.query);
        }

    });

    App.Views.Collection_view=Backbone.View.extend({
        initialize:function(){
            this.collection.on('add',this.addOne,this);
        },
        tagName:"ul",
        render:function(){


            this.collection.each(this.addOne,this);
            return this;
        },
        addOne:function(imagemodel){
            var tempView=new App.Views.Image_View({model:imagemodel});
            this.$el.append(tempView.render().el);

        },
        events:{
            'click body#id_button':'ShowAlert'

        },
        ShowAlert:function(){
            alert("dfbvdfb")
        }
    });


    $('#id_button').click(function(){
        var text=$("#input_qeary").val();
        var im_col=new App.Collection.Images();
        im_col.query=text;
        im_col.fetch();
        var  image_col_view=new  App.Views.Collection_view({collection:im_col});
        image_col_view.render();
        $('#fotka').html(image_col_view.$el);

    });




});
















