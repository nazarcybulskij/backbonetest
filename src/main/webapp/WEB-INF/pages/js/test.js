$(function(){

    window.App={
        Models:{},
        Views:{},
        Collection:{}
    };

    Backbone.emulateHTTP=true;
    Backbone.emulateJSON=true;

    /* _.templateSettings = {
     interpolate: /\<\@\=(.+?)\@\>/gim,
     evaluate: /\<\@([\s\S]+?)\@\>/gim,
     escape: /\<\@\-(.+?)\@\>/gim
     };*/


    App.Models.Task=Backbone.Model.extend({


        initialize : function(){
            this.on("invalid",function(model,error){
                alert(error);
            });
        },

        validate:function(attrs){
            //console.log(attrs);
            if (!$.trim(attrs.title)){
                return 'імя не  має бути пустим';
            }
        }


    });

    App.Views.TaskView=Backbone.View.extend({
        initialize:function(){
            this.model.on('change',this.render,this);
        },
        tagName:'li',
        template: _.template($('#my_first_tamplate').html()),
        render:function(){
            var template=this.template(this.model.toJSON());
            console.log(template);
            this.$el.html(template);

            return this;
        },
        events:{
            'click #edit':'ShowAlert'

        },
        ShowAlert:function(){
            var newname=prompt(' як тебе звати ?? ' , this.model.get('title'));
            this.model.set('title',newname,{validate:true});
        }

    });

    App.Collection.TaskCol=Backbone.Collection.extend({
        initialize:function(){
            console.log(this);
        },
        model:App.Models.Task ,
        query:"Google",
        url:function(){
            return  "http://localhost:8080/test"+"?query=" + encodeURIComponent(this.query);
        }
    });

    App.Views.Tasks=Backbone.View.extend({
        tagName:'ul',
        render:function(){
            this.collection.each(this.addOne,this);

             this.collection.fetch({
                success: function(collection, response){
                    _.each(collection.models,function(model) {
                            console.log(model.toJSON());
                     })
                 }
             })

            return this;
        },
        addOne:function(task){
            var taskView=new App.Views.TaskView({model:task});
            this.$el.append(taskView.render().el);


        }

    });




    var tasks=new App.Collection.TaskCol([
        {
            title:'Nasar',
            url:23
        },
        {
            title:'Vasa',
            url:21
        }
    ]);
    console.log(tasks);




    var tasksView=new App.Views.Tasks({collection:tasks});
    tasks.fetch()  ;
    tasksView.render();
    $('#fotka').html(tasksView.$el);
    var temp=$("body").find('img');
    alert(temp.addClass('myphotos'))
    $(".myphotos").glisse({speed: 500, changeSpeed: 550, effect:'roll', fullscreen: false});


});




























