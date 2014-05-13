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
            artist:"",
            track :"",
            url:""
        }
    });

    App.Views.Image_View=Backbone.View.extend({
        initialize:function(){
            this.model.on('change',this.render,this);
        },
        template: _.template($('#searchelement').html()),
        tagName:'p',
        render:function(){
            var template=this.template(this.model.toJSON());
            this.$el.html(template);
            return this;
        }  ,
        events:{
          'click button':'testfunction'
        },
        testfunction:function(){
            var template=(_.template($('#player').html()))(this.model.toJSON());
            $('#player_id').html("")
            $('#player_id').append(template);
        }


    });

    App.Collection.Images=Backbone.Collection.extend({
        model:App.Models.Image,
        query:"",
        url:function(){
            //return  "http://backnonetest-zhybulskij.rhcloud.com/searchvkmusic?query=" + encodeURIComponent(this.query);
            return  "http://localhost:8080/searchvkmusic"+"?query=" + encodeURIComponent(this.query);
        }

    });

    App.Views.Collection_view=Backbone.View.extend({
        initialize:function(){
            //this.el = $('.searchlist');
            this.collection.on('add',this.addOne,this);
        },
        tagName:'div',

        render:function(){
            this.collection.each(this.addOne,this);
            console.log(this.el);
            $('#search').append(this.el);
            return this;
        },
        addOne:function(imagemodel){
            var tempView=new App.Views.Image_View({model:imagemodel});
            this.$el.append(tempView.render().el);

        }
    });

    $('#button_go').click(function(){
            var text=$("#vk_search").val();
            var im_col=new App.Collection.Images();
            im_col.query=text;
            var  image_col_view=new  App.Views.Collection_view({collection:im_col});
            im_col.fetch();
            $('#search').append(image_col_view.el)
        }
    );














});

















