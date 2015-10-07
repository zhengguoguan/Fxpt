(function($){
    $.fn.extend({
        iSelect: function(options){
            var iset = {
                name: $('.selectitem'), //����
                select: $('.selectitem>dl'), //dl�б�
                dropCSS: 'drop', //�ղ�״̬dt����ʽ
                shrinkCSS: 'shrink', //չ��״̬dt����ʽ
                hoverCSS: 'hover', //��껮��ddʱ����ʽ
                clearTime: 100, //�����û����ٻ�����������ʱ��(ms)
                dropTime: 100, //չ��ʱ��(ms)
                shrinkTime: 100, //����ʱ��(ms)
                selectVal: null//ѡ���ֵ������Ԫ����
            }            
            options=$.extend(iset, options || {});
            var mainHeight = iset.name.height();//�����߶�
            var selectHeight = iset.select.height(); //dlʵ�ʸ߶�
            var currentVal = iset.selectVal.val();
            var curTxt = iset.select.find('dt').html(currentVal); //dtĬ��html����
            var self = null;
            var hoverElem = null; //�����û����ٻ���ʱ�����¼�
            iset.name.each(function(){
                $(this).hover(function(){
                    self = this;
                    hoverElem = setTimeout(function(){
                        $(self).stop(true, false).animate({ //��껮��ʱ,չ��dl
                            height: selectHeight
                        }, iset.dropTime);
                        if ($(self).find('dt').html() == curTxt) { //�ж��Ƿ���ѡ�������б�,������ı�dt��ʽ
                            $(self).find('dt').attr('class', iset.dropCSS);
                        }
                        //dd������¼�
                        $(self).find('dd').mouseover(function(){
                            $(this).addClass(iset.hoverCSS).siblings().removeClass(iset.hoverCSS);
                        }).mousedown(function(){ //�����ʱȡֵ������dt
                            $(self).find('dt').html($(this).html()).attr('class', $(this).attr('class'));
							//Ϊ����ֵ                            
							if(iset.selectVal){
								iset.selectVal.val($(this).html());								
							}
                            $(self).stop(true, false).animate({
                                height: mainHeight
                            }, iset.shrinkTime);
                        }).removeClass(iset.hoverCSS);
                    }, iset.clearTime);
                }, function(){
                    //����Ƴ��󴥷����¼�
                    clearTimeout(hoverElem);
                    $(self).stop(true, false).animate({
                        height: mainHeight
                    }, iset.shrinkTime);
                    if ($(self).find('dt').html() == curTxt) {
                        $(self).find('dt').attr('class', iset.shrinkCSS);
                    }
                });
            })
        }
    })
})(jQuery);