package com.example.music;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

import java.io.IOException;


public class Music_Activity extends AppCompatActivity implements View.OnClickListener{
    private static SeekBar sb;
    private static TextView tv_progress,tv_total,name_song,textView1,textView_lrc;
    private ObjectAnimator animator;
    private MusicService.MusicControl musicControl;
    private static ImageView music_pic,imageView_xihuan,imageView_xunhuan;
    int i;
    String name,sname,id;
    Intent intent1,intent2;
    MyServiceConn conn;
    private ImageButton musicbutton;
    private boolean isUnbind =false;//记录服务是否被解绑
    public int change=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        intent1=getIntent();
        init();
    }
    @SuppressLint("SetTextI18n")
    private void init(){
        tv_progress=(TextView)findViewById(R.id.tv_progress);
        tv_total=(TextView)findViewById(R.id.tv_total);
        sb=(SeekBar)findViewById(R.id.sb);
        name_song=(TextView)findViewById(R.id.song_name);
        music_pic=(ImageView)findViewById(R.id.iv_music);
        imageView_xihuan=(ImageView)findViewById(R.id.xihuan);
        imageView_xunhuan=(ImageView)findViewById(R.id.xunhuan);
        textView1=(TextView)findViewById(R.id.show_lrc);
        textView_lrc=(TextView)findViewById(R.id.lrc);
        musicbutton = (ImageButton) findViewById(R.id.btn_pause);

        //findViewById(R.id.btn_play).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        //findViewById(R.id.btn_continue_play).setOnClickListener(this);
        findViewById(R.id.btn_exit).setOnClickListener(this);
        findViewById(R.id.xihuan).setOnClickListener(this);
        findViewById(R.id.xunhuan).setOnClickListener(this);


        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_pre).setOnClickListener(this);
        musicbutton.setImageResource(R.drawable.tingzhi);//最开始按键是播放状态




        name=intent1.getStringExtra("name");
        //id=intent1.getStringExtra("id");
        sname=name;
        System.out.println(sname);
        name_song.setText(name);
        intent2=new Intent(this,MusicService.class);//创建意图对象
        conn=new MyServiceConn();//创建服务连接对象
        textView_lrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
        intent.setClass(Music_Activity.this,lrc.class);
        intent.putExtra("name",sname);
        startActivity(intent);
            }
        });
        if(name.equals("双笙 - 故梦")){
            textView1.setText("故梦 - 伦桑\n" +
                    "旧忆就像一扇窗\n" +
                    "推开了就再难合上\n" +
                    "谁踩过枯枝轻响\n" +
                    "萤火绘着画屏香\n" +
                    "为谁拢一袖芬芳\n" +
                    "红叶的信笺情意绵长\n" +
                    "他说就这样去流浪\n" +
                    "到美丽的地方\n" +
                    "谁的歌声轻轻\n" +
                    "轻轻唱\n" +
                    "谁的泪水静静淌\n" +
                    "那些年华都付作过往\n" +
                    "他们偎依着彼此说好要面对风浪\n" +
                    "又是一地枯黄\n" +
                    "枫叶红了满面秋霜\n" +
                    "这场故梦里\n" +
                    "人生如戏唱\n" +
                    "还有谁登场\n" +
                    "昏黄烛火轻摇晃\n" +
                    "大红盖头下谁彷徨\n" +
                    "流泪的花和荣喜堂\n" +
                    "静静放一旁\n" +
                    "回忆像默片播放\n" +
                    "刻下一寸一寸旧时光\n" +
                    "他说就这样去流浪\n" +
                    "到美丽的地方\n" +
                    "谁的歌声轻轻\n" +
                    "轻轻唱\n" +
                    "谁的泪水静静淌\n" +
                    "愿化一双鸟儿去飞翔\n" +
                    "任身后哭号嘶喊着也追不上\n" +
                    "又一年七月半晚风凉\n" +
                    "斜阳渐矮只影长\n" +
                    "这场故梦里\n" +
                    "孤桨声远荡\n" +
                    "去他乡遗忘");}
        if(name.equals("GAI周延 _ 刘煜 - 沧海一声笑")){
            textView1.setText("沧海一声笑 (Live) - GAI\n" +
                    "词：黄霑\n" +
                    "曲：黄霑\n" +
                    "原唱：许冠杰/黄霑/罗大佑/徐克\n" +
                    "Rap词：周延GAI/临渡\n" +
                    "编曲：宋阳\n" +
                    "Program：宋阳\n" +
                    "定位制作人：刘洲\n" +
                    "琵琶：李琼\n" +
                    "中国大鼓：刘效松（乐队）/郑瑀\n" +
                    "沧海一声笑\n" +
                    "滔滔两岸潮\n" +
                    "浮沉随浪 记今朝\n" +
                    "苍天笑 纷纷世上潮\n" +
                    "谁负谁胜出天知晓\n" +
                    "何处 笙箫\n" +
                    "琴声 入鞘\n" +
                    "我以黄河水 蘸墨挥毫\n" +
                    "可路正遥 今生如刀\n" +
                    "若一晌喝醉看我未老\n" +
                    "要海阔天高\n" +
                    "高声大笑\n" +
                    "笑过勾销\n" +
                    "恩怨 妄念 贪恋 再逍遥\n" +
                    "翻开你人生的简历\n" +
                    "发现你啥子都没得\n" +
                    "该如何看待你自己\n" +
                    "时间该咋个切支配\n" +
                    "是哪些人混不下去\n" +
                    "土豆吃起去下力\n" +
                    "选哪条路都不好走\n" +
                    "啥子命我都不抗拒\n" +
                    "我命硬学不来弯腰\n" +
                    "一山还要比一山高\n" +
                    "沧海一声笑 滔滔两岸潮\n" +
                    "浮沉随浪 记今朝\n" +
                    "苍天笑 纷纷世上潮\n" +
                    "谁负谁胜出天知晓\n" +
                    "天 地 人 和\n" +
                    "恩怨似云烟转眼飞很远\n" +
                    "早看破红尘\n" +
                    "也不与世人周旋\n" +
                    "逍遥的侠客\n" +
                    "要踏上江湖归路\n" +
                    "坐禅或修行\n" +
                    "不枉此生虚度\n" +
                    "What we do like them\n" +
                    "他们抓不住感觉\n" +
                    "盖九天揽月写广陵散诀\n" +
                    "孩子还在勤工俭学\n" +
                    "闪耀的钻石不会埋没\n" +
                    "走一遭发现从没来过\n" +
                    "到处招摇过市在浑天过日\n" +
                    "歌词唱到口吐白沫\n" +
                    "我命硬学不来弯腰\n" +
                    "一山还要比一山高\n" +
                    "沧海一声笑 滔滔两岸潮\n" +
                    "浮沉随浪 记今朝\n" +
                    "江山笑 烟雨遥\n" +
                    "涛浪淘尽红尘世俗知多少\n" +
                    "清风笑 竟惹寂寥\n" +
                    "豪情还剩 衣襟晚照\n" +
                    "豪情还剩 衣襟晚照");}
        if(name.equals("爱殇")){
            textView1.setText("爱殇\n" +
                    "暮色起看天边斜阳\n" +
                    "恍惚想起你的脸庞\n" +
                    "毕竟回想 难免徒增感伤\n" +
                    "轻叹息 我们那些好时光\n" +
                    "夜未央繁星落眼眶\n" +
                    "拾一段柔软的光芒\n" +
                    "清风过 曳烛光\n" +
                    "独舞无人欣赏\n" +
                    "留 花瓣随风飘荡\n" +
                    "我要将过往都储藏\n" +
                    "编一段美好的梦想\n" +
                    "也许幻想 到最后会更伤\n" +
                    "假欢畅 又何妨 无人共享\n" +
                    "你曾经是我的边疆\n" +
                    "抵挡我所有的悲伤\n" +
                    "西风残 故人往\n" +
                    "如今被爱流放\n" +
                    "困 在了眼泪中央\n" +
                    "啊 啊（轻解霓裳 咽泪换笑妆）\n" +
                    "啊 啊（等你戎装 去呼啸沧桑）\n" +
                    "啊（过往终究止不住流淌）\n" +
                    "啊（去御剑飞翔）\n" +
                    "啊（也许会飞出这感伤）\n" +
                    "暮色起看天边斜阳\n" +
                    "夜未央星河独流淌\n" +
                    "天晴朗 好风光\n" +
                    "若你不在身旁\n" +
                    "能 上苍穹又怎样\n" +
                    "船过空港 将寂寞豢养\n" +
                    "旷野霜降 低垂了泪光\n" +
                    "啊（是谁陨落了我的太阳）\n" +
                    "啊（是你的模样）\n" +
                    "啊（带走我所有的光芒）\n" +
                    "扬帆远航 敌不过彷徨\n" +
                    "奈何流放 敌不过苍凉\n" +
                    "啊（我要潜入回忆的汪洋）\n" +
                    "啊（寻你的模样）\n" +
                    "啊（唯有你是我的天堂）\n" +
                    "唯有你是我的 天堂");
        }
        if(name.equals("如约而至")){
            textView1.setText("如约而至 - 许嵩\n" +
                    "词：许嵩\n" +
                    "曲：许嵩\n" +
                    "如果早知道那天是我们最后一面\n" +
                    "我绝不会和你谈论琐事浪费时间\n" +
                    "人生里看似偶然却又必经的告别\n" +
                    "无约而至 无人可免\n" +
                    "有一回置身某个似曾相识的场景\n" +
                    "那一刻我忽然感应你就在我身边\n" +
                    "过去 当下 未来未必确有其界限\n" +
                    "或许爱能穿越时间 抵达永远\n" +
                    "等到秋叶终于金黄\n" +
                    "等到华发悄然苍苍\n" +
                    "我们相约老地方\n" +
                    "等到人已不再奔忙\n" +
                    "等到心也不再轻狂\n" +
                    "我们相约老地方\n" +
                    "等到记忆只剩精华\n" +
                    "等到笑容不掺伪装\n" +
                    "我们相约老地方\n" +
                    "等到释怀所有悲伤\n" +
                    "等到体谅世事无常\n" +
                    "我们相约老地方\n" +
                    "有一回置身某个似曾相识的场景\n" +
                    "那一刻我忽然感应你就在我身边\n" +
                    "过去 当下 未来未必确有其界限\n" +
                    "或许爱能穿越时间 抵达永远\n" +
                    "等到秋叶终于金黄\n" +
                    "等到华发悄然苍苍\n" +
                    "我们相约老地方\n" +
                    "等到人已不再奔忙\n" +
                    "等到心也不再轻狂\n" +
                    "我们相约老地方\n" +
                    "等到记忆只剩精华\n" +
                    "等到笑容不掺伪装\n" +
                    "我们相约老地方\n" +
                    "等到释怀所有悲伤\n" +
                    "等到体谅世事无常\n" +
                    "我们相约老地方\n" +
                    "等到记忆只剩精华\n" +
                    "等到笑容不掺伪装\n" +
                    "我们相约老地方\n" +
                    "等到释怀所有悲伤\n" +
                    "等到体谅世事无常\n" +
                    "我们相约老地方");
        }
        if(name.equals("雨幕")){
            textView1.setText("雨幕\n"+
                    "桃花岛屿之巅\n" +
                    "一座残碑镇住想念\n" +
                    "故人题词十年前\n" +
                    "而今古苔已横啮\n" +
                    "冬深春浅时节\n" +
                    "岛畔福船入港寒夜\n" +
                    "蒙蒙雾气里跃上甲板\n" +
                    "道声勿念\n" +
                    "海上三日无言 卧听雨打舷\n" +
                    "邻舱客叩门寒暄\n" +
                    "他鹤发童颜 举着夜光杯\n" +
                    "与我对饮舷窗前\n" +
                    "窗外潇潇的雨幕里\n" +
                    "飘然一曲诱我侧耳听\n" +
                    "水面箫中剑的倒影\n" +
                    "是爱中藏恨的诗句\n" +
                    "我从潇潇的雨幕里\n" +
                    "遥望漉雪千山都过尽\n" +
                    "隔海隔山你的背影\n" +
                    "冬深春浅时节\n" +
                    "岛畔福船入港寒夜\n" +
                    "蒙蒙雾气里跃上甲板\n" +
                    "道声勿念\n" +
                    "海上三日无言 卧听雨打舷\n" +
                    "邻舱客叩门寒暄\n" +
                    "他鹤发童颜 举着夜光杯\n" +
                    "与我对饮舷窗前\n" +
                    "窗外潇潇的雨幕里\n" +
                    "飘然一曲诱我侧耳听\n" +
                    "水面箫中剑的倒影\n" +
                    "是爱中藏恨的诗句\n" +
                    "我从潇潇的雨幕里\n" +
                    "遥望漉雪千山都过尽\n" +
                    "隔海隔山你的背影\n" +
                    "窗外潇潇的雨幕里\n" +
                    "飘然一曲诱我侧耳听\n" +
                    "水面箫中剑的倒影\n" +
                    "是爱中藏恨的诗句\n" +
                    "我在潇潇的雨幕里\n" +
                    "漉雪千山都过尽\n" +
                    "隔海隔山你的背影\n" +
                    "绯泊之间喘息渐停");
        }
        if(name.equals("棠梨煎雪")){
            textView1.setText("棠梨煎雪\n" +
                    "演唱：银临\n" +
                    "作词：商连\n" +
                    "作曲：灰原穷\n" +
                    "编曲：灰原穷\n" +
                    "混缩：灰原穷\n" +
                    "专辑：腐草为萤\n" +
                    "歌词编辑：友友音悦（罗显友）\n" +
                    "青鲤来时遥闻春溪声声碎\n" +
                    "嗅得手植棠梨初发轻黄蕊\n" +
                    "待小暑悄过 新梨渐垂\n" +
                    "来邀东邻女伴撷果缓缓归\n" +
                    "旧岁采得枝头细雪\n" +
                    "今朝飘落胭脂梨叶\n" +
                    "轻挼草色二三入卷\n" +
                    "细呷春酒淡始觉甜\n" +
                    "依旧是\n" +
                    "偏爱枕惊鸿二字入梦的时节\n" +
                    "烛火惺忪却可与她漫聊彻夜\n" +
                    "早春暮春 酒暖花深\n" +
                    "便好似一生心事只得一人来解\n" +
                    "岁岁花藻檐下共将棠梨煎雪\n" +
                    "自总角至你我某日辗转天边\n" +
                    "天淡天青 宿雨沾襟\n" +
                    "一年一会信笺却只见寥寥数言\n" +
                    "旧岁采得枝头细雪\n" +
                    "今朝飘落胭脂梨叶\n" +
                    "轻挼草色二三入卷\n" +
                    "细呷春酒淡始觉甜\n" +
                    "依旧是\n" +
                    "偏爱枕惊鸿二字入梦的时节\n" +
                    "烛火惺忪却可与她漫聊彻夜\n" +
                    "早春暮春 酒暖花深\n" +
                    "便好似一生心事只得一人来解\n" +
                    "岁岁花藻檐下共将棠梨煎雪\n" +
                    "自总角至你我某日辗转天边\n" +
                    "天淡天青 宿雨沾襟\n" +
                    "一年一会信笺却只见寥寥数言\n" +
                    "雨中灯市欲眠 原已萧萧数年\n" +
                    "似有故人轻叩 再将棠梨煎雪\n" +
                    "能否消得\n" +
                    "你一路而来的半生风雪");
        }
        if(name.equals("绘笔江南")){
            textView1.setText("绘笔江南 - 叶洛洛/西瓜JUN/泥鳅Niko/知性的小方块\n" +
                    "词：南岐\n" +
                    "曲：贺鑫\n" +
                    "编曲：糖番茄音乐工作组\n" +
                    "特别鸣谢：胡碧乔\n" +
                    "笛子：徐之博dizi\n" +
                    "分轨混音：Wuli包子\n" +
                    "人声混音：泥鳅Niko\n" +
                    "和声编唱：泥鳅Niko\n" +
                    "曲绘：BlazeK\n" +
                    "PV：不一定\n" +
                    "美工：池崎智央\n" +
                    "泥鳅（唐伯虎）：\n" +
                    "绘一卷花鸟对诗吟\n" +
                    "讨个游春园的意趣\n" +
                    "人间来去 身后如何评\n" +
                    "他人求利禄 此番我随意\n" +
                    "西瓜（文征明）：\n" +
                    "鱼虫山石并杨柳青\n" +
                    "邀山河万物作诗笔\n" +
                    "小山停云 再二三知己\n" +
                    "尽各家风致 笔下才淋漓\n" +
                    "洛洛/方块：\n" +
                    "春宴春酒何不与你共饮\n" +
                    "共赏熏风细软 吹开好光景\n" +
                    "醉极也狂言 世人看分明\n" +
                    "天下文榜自有我才名\n" +
                    "西瓜/泥鳅：\n" +
                    "桃花牡丹一并纳入衣襟\n" +
                    "结交这团簇缤纷江南灵气\n" +
                    "醉极也贪眠 花下半梦半醒\n" +
                    "恍然天地间我留名\n" +
                    "方块（祝枝山）：\n" +
                    "百家诗书百样风流\n" +
                    "风雅平生都卷成册\n" +
                    "饮酒吟诗 也自得其乐\n" +
                    "一梦十九载 凭谁来添色\n" +
                    "洛洛（徐祯卿）：\n" +
                    "无情春秋多情月色\n" +
                    "笔墨下盛妆点山河\n" +
                    "名满士林 虚名赠诗歌\n" +
                    "再看扬州花 摘落不可得\n" +
                    "西瓜/泥鳅：\n" +
                    "春宴春酒何不与你共饮\n" +
                    "共赏熏风细软 吹开好光景\n" +
                    "醉极也狂言 世人看分明\n" +
                    "天下文榜自有我才名\n" +
                    "洛洛/方块：\n" +
                    "桃花牡丹一并纳入衣襟\n" +
                    "结交这团簇缤纷江南灵气\n" +
                    "醉极也贪眠 花下半梦半醒\n" +
                    "恍然天地间我留名\n" +
                    "西瓜：\n" +
                    "青山绿水何处与你共饮\n" +
                    "洛洛：\n" +
                    "共赏芳菲错落 抬眼好光景\n" +
                    "泥鳅：\n" +
                    "人生得意时 不若逢知音\n" +
                    "方块：\n" +
                    "天下文榜自有君才名\n" +
                    "合：\n" +
                    "风月炊烟一并纳入衣襟\n" +
                    "结交这少年人红尘烟火气\n" +
                    "辞别山河去 年岁半梦半醒\n" +
                    "恍然天地间我留名");
        }
        if(name.equals("恣意千年")){
            textView1.setText("只有影子、云の泣、封茗囧菌、HITA、Aki阿杰、赵方婧、玄觞、王梓钰 - 瓷意千年\n" +
                    "作词：小玖州\n" +
                    "作曲：李星月\n" +
                    "编曲：王景（零小七）\n" +
                    "和声编写：李星月\n" +
                    "男和声：Jason嘉\n" +
                    "女和声：足音\n" +
                    "后期：王韩一淋\n" +
                    "母带：子棋\n" +
                    "题字：叶巇\n" +
                    "背景画师：叶巇\n" +
                    "美工：曲画鸢\n" +
                    "PV：咔擦一刀\n" +
                    "文案：小玖州\n" +
                    "总策划：Jason嘉\n" +
                    "协助策划：小玖州\n" +
                    "只有影子：\n" +
                    "空山烟雨朦胧\n" +
                    "纸伞晕染了青瞳\n" +
                    "天近晚晴邂逅\n" +
                    "在世间初梦相逢\n" +
                    "云の泣：\n" +
                    "翠色千峰\n" +
                    "越泥玉玲珑\n" +
                    "臣庶传 王孙醉\n" +
                    "赠一折清风\n" +
                    "封茗囧菌：\n" +
                    "雪落孤城惊鸿\n" +
                    "嫩荷涵露似瑶宫\n" +
                    "箸击山水妙空\n" +
                    "闻莲动亦或心动\n" +
                    "Hita：\n" +
                    "跨越过岁月山河\n" +
                    "兼程相送\n" +
                    "予你风情万种\n" +
                    "只有影子+云の泣：\n" +
                    "赏过灯火阑珊处\n" +
                    "轻舞慢 杏花眼\n" +
                    "吟过唐词三百篇\n" +
                    "迁客赴 共婵娟\n" +
                    "封茗囧菌+Hita：\n" +
                    "铺纸提写 香得永年\n" +
                    "春秋换来 红泥一点\n" +
                    "只有影子+Hita：\n" +
                    "陶舍雕花素手纤\n" +
                    "水中莲 馨香传\n" +
                    "云の泣+封茗囧菌：\n" +
                    "一寸相思复开轩\n" +
                    "渔家火 照不眠\n" +
                    "只有影子+云の泣+封茗囧菌+Hita：\n" +
                    "黛眼初见\n" +
                    "清风有情托付琴弦\n" +
                    "足音：\n" +
                    "缤纷落满春源桃\n" +
                    "陌上早 知多少\n" +
                    "杨柳外乡俚歌谣\n" +
                    "林中鸟 向云霄\n" +
                    "雨打芭蕉 风声轻巧\n" +
                    "谁家笙箫 歌尽窈窕\n" +
                    "她又藏在 哪座家窑\n" +
                    "炊烟无人敢惊扰\n" +
                    "马儿跑 鱼儿摇\n" +
                    "世外三两醉今宵\n" +
                    "担中挑 画里瞧\n" +
                    "千古难描\n" +
                    "枕青石明月松间照\n" +
                    "烟花里笑偕老偕老\n" +
                    "Aki阿杰：\n" +
                    "玉壶春江贺送\n" +
                    "窑藏龙泉敬白首\n" +
                    "素意浑然天成\n" +
                    "雕卷帘西风消瘦\n" +
                    "赵方婧：\n" +
                    "掷笔江流 水墨作星宿\n" +
                    "百花嗅 艳芳斗\n" +
                    "自添为不朽\n" +
                    "玄觞：\n" +
                    "祥瑞齐天来寿\n" +
                    "伴行舟鸳鸯同游\n" +
                    "秀色不染通透\n" +
                    "再勾勒行云淡愁\n" +
                    "王梓钰：\n" +
                    "匠心借山水缠枝\n" +
                    "绘入薄釉\n" +
                    "勾画惹风流\n" +
                    "Aki阿杰+赵方婧：\n" +
                    "赏过灯火阑珊处\n" +
                    "轻舞慢 杏花眼\n" +
                    "只有影子+赵方婧：\n" +
                    "吟过唐词三百篇\n" +
                    "迁客赴 共婵娟\n" +
                    "玄觞+王梓钰：\n" +
                    "铺纸提写 香得永年\n" +
                    "春秋换来 红泥一点\n" +
                    "Aki阿杰+王梓钰：\n" +
                    "陶舍雕花素手纤\n" +
                    "水中莲 馨香传\n" +
                    "赵方婧+玄觞：\n" +
                    "一寸相思复开轩\n" +
                    "渔家火 照不眠\n" +
                    "Aki阿杰+赵方婧+玄觞+王梓钰：\n" +
                    "黛眼初见\n" +
                    "清风有情托付琴弦\n" +
                    "只有影子+云の泣+封茗囧菌+Hita：\n" +
                    "松声云影自壶天\n" +
                    "丹青卷 九曲泉\n" +
                    "雕龙描凤别人间\n" +
                    "万般皆 青山携\n" +
                    "只有影子+封茗囧菌：\n" +
                    "釉蘸漪涟 刀销圆月\n" +
                    "云の泣+Hita：\n" +
                    "山水带竹 百花尽现\n" +
                    "Aki阿杰+赵方婧+玄觞+王梓钰：\n" +
                    "卧听江城一夜雪\n" +
                    "且吟酌 思华年\n" +
                    "细搅墨香入筠笼\n" +
                    "空樽前 绕绿烟\n" +
                    "合：\n" +
                    "兰斛香眠\n" +
                    "把盏敬谢盛世千年\n" +
                    "只有影子+云の泣：\n" +
                    "缥沫弄颜 沧桑添\n" +
                    "封茗囧菌+Hita：\n" +
                    "雨过天青 深巷斜\n" +
                    "Aki阿杰+赵方婧：\n" +
                    "知音闻弦 风月无边\n" +
                    "玄觞+王梓钰：\n" +
                    "世代相传静谧人间\n" +
                    "瓷意千年");
        }
        if(name.equals("黯然销魂")){
            textView1.setText("玄觞 - 黯然销魂\n" +
                    "作词：玄觞\n" +
                    "作曲：玄觞\n" +
                    "枝头薄雪尚存\n" +
                    "远山传来暮鼓声声\n" +
                    "斜阳散了余温\n" +
                    "凉意入骨几寸\n" +
                    "渡口半盏摇灯\n" +
                    "经历多少日落月升\n" +
                    "流水脉脉淌过世事浮尘\n" +
                    "向来风花雪月动人\n" +
                    "相偎桃林醉闻晚风\n" +
                    "抚琴对饮仗剑红尘\n" +
                    "冬去春来寒暑几更\n" +
                    "情深一寸伤多一分\n" +
                    "如梦江湖如戏人生是我沉沦\n" +
                    "桃花绯雨故城荡着烟波\n" +
                    "十里纷纷\n" +
                    "疏影斜月河灯湖畔悠悠琴声\n" +
                    "仿若昨夜星辰随风摇落\n" +
                    "弦月微冷\n" +
                    "浮光掠影最是黯然销魂\n" +
                    "从来风花雪月动人\n" +
                    "千里雨霁水天难分\n" +
                    "若说最美不过重逢\n" +
                    "他日久别偶遇故人\n" +
                    "话说三分痛却十分\n" +
                    "与你错失太多前尘\n" +
                    "嗯哼\n" +
                    "从来风花雪月动人\n" +
                    "千里雨霁水天难分\n" +
                    "最美不过重逢\n" +
                    "他日久别偶遇故人\n" +
                    "话说三分\n" +
                    "啊\n" +
                    "与你错失太多前尘\n" +
                    "哦\n" +
                    "如梦江湖如戏人生是我沉沦");
        }
        if(name.equals("千梦")){
            textView1.setText("双笙、玄觞 - 千梦\n" +
                    "作词：原晞\n" +
                    "作曲：灰原穷\n" +
                    "画一笔\n" +
                    "炊烟十里\n" +
                    "依偎着\n" +
                    "人间朝夕\n" +
                    "故事中\n" +
                    "原来是你\n" +
                    "一生山水为迹\n" +
                    "有桃之夭夭\n" +
                    "凌风过浮生缥缈\n" +
                    "回旋年少\n" +
                    "不舍唱完的歌谣\n" +
                    "你眉眼含笑\n" +
                    "我亦多情不敢老\n" +
                    "彼时光景正好\n" +
                    "不见归来\n" +
                    "相思寄于山海\n" +
                    "风在城外\n" +
                    "裁去春秋几载\n" +
                    "似曾是桥上轻雨伞开\n" +
                    "你着一袭素白\n" +
                    "回首千千对影燕徘徊\n" +
                    "借一缕\n" +
                    "月色如洗\n" +
                    "点染成\n" +
                    "相思无题\n" +
                    "曲终后\n" +
                    "依稀是你\n" +
                    "从此天涯为期\n" +
                    "兰舟催发早\n" +
                    "执手在苏堤春晓\n" +
                    "岁岁燕丘\n" +
                    "策马天下知多少\n" +
                    "听雨花事了\n" +
                    "春风却莫观其妙\n" +
                    "彼时光景正好\n" +
                    "不见归来\n" +
                    "相思寄于山海\n" +
                    "风在城外\n" +
                    "裁去春秋几载\n" +
                    "似曾是桥上轻雨伞开\n" +
                    "你着一袭素白\n" +
                    "回首千千对影燕徘徊\n" +
                    "举杯邀月\n" +
                    "醉在桃溪春野\n" +
                    "南海飞雪\n" +
                    "吻过你的眉睫\n" +
                    "想那年树下回眸无邪\n" +
                    "恰逢花雨未歇\n" +
                    "一梦千千让光阴重叠");
        }
        if(name.equals("ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)")){
            textView1.setText("BLACKPINK - AS IF IT'S YOUR LAST(Japan Version BLACKPINK 2019-2020 WORLD TOUR IN YOUR AREA -TOKYO DOME-)\n" +
                    "作词：TEDDY、BrotherSu、CHOICE37、SUNNY BOY\n" +
                    "作曲：TEDDY、FUTURE BOUNCE、Lydia Paek\n" +
                    "编曲：FUTURE BOUNCE、TEDDY\n" +
                    "Little bit of that\n" +
                    "Little bit of this\n" +
                    "Little bit of that\n" +
                    "Gimme gimme gimme\n" +
                    "Gimme gimme gimme\n" +
                    "Gimme little bit of that\n" +
                    "あなたしか見えない\n" +
                    "ドキドキしちゃうわ\n" +
                    "赤らむ顔胸の中も\n" +
                    "思い通りにいかないの\n" +
                    "ひと握りの砂のよう\n" +
                    "まるであなたは\n" +
                    "手ごわいほど\n" +
                    "惹かれるもっと\n" +
                    "思い通りにいかないの\n" +
                    "私の吐息を感じてほしい\n" +
                    "見つめていても missing you\n" +
                    "だから won't you set me free\n" +
                    "Baby もっと\n" +
                    "ぎゅっと強くずっと\n" +
                    "抱きしめて何もいらないの\n" +
                    "素直に今キスをしてほしい\n" +
                    "最後の愛のように\n" +
                    "最後のよう最後のよう\n" +
                    "最後の夜のよう love\n" +
                    "最後のよう最後のよう\n" +
                    "明日がこないよう love\n" +
                    "Uh I'ma fall in love baby\n" +
                    "You gon finna catch me\n" +
                    "Uh give you all of this baby\n" +
                    "Call me pretty and nasty\n" +
                    "Cause we gonna get it my love you can bet it on\n" +
                    "Black we gon double the stack on them whoa\n" +
                    "I be the Bonnie and you be my Clyde\n" +
                    "We ride or die\n" +
                    "X's and O's\n" +
                    "時間が過ぎても焦る未だに\n" +
                    "あなたが全てよ missing you\n" +
                    "だから won't you set me free\n" +
                    "Baby もっと\n" +
                    "ぎゅっと強くずっと\n" +
                    "抱きしめて何もいらないの\n" +
                    "素直に今キスをしてほしい\n" +
                    "最後の愛のように\n" +
                    "最後のよう最後のよう\n" +
                    "最後の夜のよう love\n" +
                    "最後のよう最後のよう\n" +
                    "明日がこないよう love\n" +
                    "1 2 3から始めるわ\n" +
                    "決して後ろ振り向かないわ\n" +
                    "私の全てを受け止めてよ\n" +
                    "誰にも邪魔させないわ\n" +
                    "BLACKPINK in your area\n" +
                    "Baby もっと\n" +
                    "ぎゅっと強くずっと\n" +
                    "抱きしめて何もいらないの\n" +
                    "素直に今キスをしてほしい\n" +
                    "最後の愛のように\n" +
                    "最後のよう最後のよう\n" +
                    "最後の夜のよう love\n" +
                    "最後のよう最後のよう\n" +
                    "明日がこないよう love");
        }


        bindService(intent2,conn,BIND_AUTO_CREATE);//绑定服务
        //为滑动条添加事件监听
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度条改变时，会调用此方法
                if (progress==seekBar.getMax()){//当滑动条到末端时，结束动画
                    animator.pause();//停止播放动画
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {//滑动条开始滑动时调用
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {//滑动条停止滑动时调用
                //根据拖动的进度改变音乐播放进度
                int progress=seekBar.getProgress();//获取seekBar的进度
                musicControl.seekTo(progress);//改变播放进度
            }
        });
        ImageView iv_music=(ImageView)findViewById(R.id.iv_music);
        String position= intent1.getStringExtra("position");
        i=parseInt(position);
        //iv_music.setImageResource(frag1.icons[1]);
        iv_music.setImageResource(frag1.icons[i]);


        animator=ObjectAnimator.ofFloat(iv_music,"rotation",0f,360.0f);
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);//-1表示设置动画无限循环
    }


    @SuppressLint("HandlerLeak")
    public static Handler handler=new Handler(){//创建消息处理器对象
        //在主线程中处理从子线程发送过来的消息
        @Override
        public void handleMessage(Message msg){
            Bundle bundle=msg.getData();//获取从子线程发送过来的音乐播放进度
            int duration=bundle.getInt("duration");
            int currentPosition=bundle.getInt("currentPosition");
            sb.setMax(duration);
            sb.setProgress(currentPosition);
            //歌曲总时长
            int minute=duration/1000/60;
            int second=duration/1000%60;
            String strMinute=null;
            String strSecond=null;
            if(minute<10){//如果歌曲的时间中的分钟小于10
                strMinute="0"+minute;//在分钟的前面加一个0
            }else{
                strMinute=minute+"";
            }
            if (second<10){//如果歌曲中的秒钟小于10
                strSecond="0"+second;//在秒钟前面加一个0
            }else{
                strSecond=second+"";
            }
            tv_total.setText(strMinute+":"+strSecond);
            //歌曲当前播放时长
            minute=currentPosition/1000/60;
            second=currentPosition/1000%60;
            if(minute<10){//如果歌曲的时间中的分钟小于10
                strMinute="0"+minute;//在分钟的前面加一个0
            }else{
                strMinute=minute+" ";
            }
            if (second<10){//如果歌曲中的秒钟小于10
                strSecond="0"+second;//在秒钟前面加一个0
            }else{
                strSecond=second+" ";
            }
            tv_progress.setText(strMinute+":"+strSecond);
        }
    };
     class MyServiceConn implements ServiceConnection{//用于实现连接服务
        @Override
        public void onServiceConnected(ComponentName name, IBinder service){
            musicControl=(MusicService.MusicControl) service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name){

        }
    }
    private void unbind(boolean isUnbind){
        if(!isUnbind){//判断服务是否被解绑
            musicControl.pausePlay();//暂停播放音乐
            unbindService(conn);//解绑服务
        }
    }

    private void LoveBotton(){
        String filename = null;
        String content = null;
        MyInternalStorage myInternal = null;
        filename = name;
        content = "1232113";
        myInternal = new MyInternalStorage(Music_Activity.this);
        try {
            myInternal.save(filename, content);
            Toast.makeText(Music_Activity.this, "保存成功",
                    Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(Music_Activity.this, "保存文件失败",
                    Toast.LENGTH_SHORT).show();
        }

    }
    private void LoveBottonon(){
        MyInternalStorage myInternal = null;
        String filename = null;
        String content = null;
        filename = name;
        myInternal = new MyInternalStorage(Music_Activity.this);
        myInternal.delete(filename);
        Toast.makeText(Music_Activity.this, "删除成功",
                Toast.LENGTH_SHORT).show();
    }
     int status = 0x11;
     int status1 = 0x22;
     int status2 = 0x33;

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
         String position= intent1.getStringExtra("position");
           int i=parseInt(position);
        switch (v.getId()){
            case R.id.xihuan://喜欢点击事件实现
                if(status1 == 0x22){
                    imageView_xihuan.setImageResource(R.drawable.music_xihuan_dark);
                    //Toast.makeText(Music_Activity.this,"添加到喜欢",Toast.LENGTH_SHORT).show();
                    LoveBotton();
                    status1 = 0x21;
                }
                else if(status1 == 0x21){
                    imageView_xihuan.setImageResource(R.drawable.music_xihuan_light);
                    //Toast.makeText(Music_Activity.this,"已从喜欢删除",Toast.LENGTH_SHORT).show();
                    LoveBottonon();
                    status1 = 0x22;
                }
                    break;
            case R.id.xunhuan://列表及循环，随机播放点击事件实现
                if(status2 == 0x33){
                    imageView_xunhuan.setImageResource(R.drawable.liebiaoxunhuan);
                    Toast.makeText(Music_Activity.this,"列表循环",Toast.LENGTH_SHORT).show();
                    status2 = 0x31;
                }
                else if(status2 == 0x31){
                    imageView_xunhuan.setImageResource(R.drawable.suijibofang);
                    Toast.makeText(Music_Activity.this,"随机播放",Toast.LENGTH_SHORT).show();
                    status2 = 0x30;
                }
                else if(status2 == 0x30){
                    imageView_xunhuan.setImageResource(R.drawable.danquxunhuan);
                    Toast.makeText(Music_Activity.this,"单曲循环",Toast.LENGTH_SHORT).show();
                    status2 = 0x33;
                }
                break;
            case R.id.btn_pause://暂停按钮点击事件
                if (status == 0x11) {
                    // 准备、并播放音乐
                    musicControl.play(i);
                    animator.start();
                    musicbutton.setImageResource(R.drawable.bofang);
                    status = 0x12;
                }
                // 原来处于播放状态
                else if (status == 0x12) {
                    // 暂停
                    musicControl.pausePlay();
                    animator.pause();
                    musicbutton.setImageResource(R.drawable.zanting);
                    // 改变为暂停状态
                    status = 0x13;
                }
                // 原来处于暂停状态
                else if (status == 0x13) {
                    // 播放
                    musicControl.continuePlay();
                    animator.start();
                    musicbutton.setImageResource(R.drawable.bofang);
                    // 改变状态
                    status = 0x12;
                }
//                musicControl.pausePlay();
//                animator.pause();
                break;
//            case R.id.btn_continue_play://继续播放按钮点击事件
//                musicControl.continuePlay();
//                animator.start();
//                break;
            case R.id.btn_exit://退出按钮点击事件
                unbind(isUnbind);
                isUnbind=true;
                finish();
                break;
            case R.id.btn_pre://播放上一首
                if((i+change)<1) {
                    Toast.makeText(Music_Activity.this, "已经是第一首了", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    change--;
                    musicbutton.setImageResource(R.drawable.bofang);
                    music_pic.setImageResource(frag1.icons[i+change]);
                    name_song.setText(frag1.name[i+change]);
                    if(frag1.name[i+change].equals("双笙 - 故梦")){
                        textView1.setText("故梦 - 伦桑\n" +
                                "旧忆就像一扇窗\n" +
                                "推开了就再难合上\n" +
                                "谁踩过枯枝轻响\n" +
                                "萤火绘着画屏香\n" +
                                "为谁拢一袖芬芳\n" +
                                "红叶的信笺情意绵长\n" +
                                "他说就这样去流浪\n" +
                                "到美丽的地方\n" +
                                "谁的歌声轻轻\n" +
                                "轻轻唱\n" +
                                "谁的泪水静静淌\n" +
                                "那些年华都付作过往\n" +
                                "他们偎依着彼此说好要面对风浪\n" +
                                "又是一地枯黄\n" +
                                "枫叶红了满面秋霜\n" +
                                "这场故梦里\n" +
                                "人生如戏唱\n" +
                                "还有谁登场\n" +
                                "昏黄烛火轻摇晃\n" +
                                "大红盖头下谁彷徨\n" +
                                "流泪的花和荣喜堂\n" +
                                "静静放一旁\n" +
                                "回忆像默片播放\n" +
                                "刻下一寸一寸旧时光\n" +
                                "他说就这样去流浪\n" +
                                "到美丽的地方\n" +
                                "谁的歌声轻轻\n" +
                                "轻轻唱\n" +
                                "谁的泪水静静淌\n" +
                                "愿化一双鸟儿去飞翔\n" +
                                "任身后哭号嘶喊着也追不上\n" +
                                "又一年七月半晚风凉\n" +
                                "斜阳渐矮只影长\n" +
                                "这场故梦里\n" +
                                "孤桨声远荡\n" +
                                "去他乡遗忘");}
                    if(frag1.name[i+change].equals("GAI周延 _ 刘煜 - 沧海一声笑")){
                        textView1.setText("沧海一声笑 (Live) - GAI\n" +
                                "词：黄霑\n" +
                                "曲：黄霑\n" +
                                "原唱：许冠杰/黄霑/罗大佑/徐克\n" +
                                "Rap词：周延GAI/临渡\n" +
                                "编曲：宋阳\n" +
                                "Program：宋阳\n" +
                                "定位制作人：刘洲\n" +
                                "琵琶：李琼\n" +
                                "中国大鼓：刘效松（乐队）/郑瑀\n" +
                                "沧海一声笑\n" +
                                "滔滔两岸潮\n" +
                                "浮沉随浪 记今朝\n" +
                                "苍天笑 纷纷世上潮\n" +
                                "谁负谁胜出天知晓\n" +
                                "何处 笙箫\n" +
                                "琴声 入鞘\n" +
                                "我以黄河水 蘸墨挥毫\n" +
                                "可路正遥 今生如刀\n" +
                                "若一晌喝醉看我未老\n" +
                                "要海阔天高\n" +
                                "高声大笑\n" +
                                "笑过勾销\n" +
                                "恩怨 妄念 贪恋 再逍遥\n" +
                                "翻开你人生的简历\n" +
                                "发现你啥子都没得\n" +
                                "该如何看待你自己\n" +
                                "时间该咋个切支配\n" +
                                "是哪些人混不下去\n" +
                                "土豆吃起去下力\n" +
                                "选哪条路都不好走\n" +
                                "啥子命我都不抗拒\n" +
                                "我命硬学不来弯腰\n" +
                                "一山还要比一山高\n" +
                                "沧海一声笑 滔滔两岸潮\n" +
                                "浮沉随浪 记今朝\n" +
                                "苍天笑 纷纷世上潮\n" +
                                "谁负谁胜出天知晓\n" +
                                "天 地 人 和\n" +
                                "恩怨似云烟转眼飞很远\n" +
                                "早看破红尘\n" +
                                "也不与世人周旋\n" +
                                "逍遥的侠客\n" +
                                "要踏上江湖归路\n" +
                                "坐禅或修行\n" +
                                "不枉此生虚度\n" +
                                "What we do like them\n" +
                                "他们抓不住感觉\n" +
                                "盖九天揽月写广陵散诀\n" +
                                "孩子还在勤工俭学\n" +
                                "闪耀的钻石不会埋没\n" +
                                "走一遭发现从没来过\n" +
                                "到处招摇过市在浑天过日\n" +
                                "歌词唱到口吐白沫\n" +
                                "我命硬学不来弯腰\n" +
                                "一山还要比一山高\n" +
                                "沧海一声笑 滔滔两岸潮\n" +
                                "浮沉随浪 记今朝\n" +
                                "江山笑 烟雨遥\n" +
                                "涛浪淘尽红尘世俗知多少\n" +
                                "清风笑 竟惹寂寥\n" +
                                "豪情还剩 衣襟晚照\n" +
                                "豪情还剩 衣襟晚照");}
                    if(frag1.name[i+change].equals("爱殇")){
                        textView1.setText("爱殇\n" +
                                "暮色起看天边斜阳\n" +
                                "恍惚想起你的脸庞\n" +
                                "毕竟回想 难免徒增感伤\n" +
                                "轻叹息 我们那些好时光\n" +
                                "夜未央繁星落眼眶\n" +
                                "拾一段柔软的光芒\n" +
                                "清风过 曳烛光\n" +
                                "独舞无人欣赏\n" +
                                "留 花瓣随风飘荡\n" +
                                "我要将过往都储藏\n" +
                                "编一段美好的梦想\n" +
                                "也许幻想 到最后会更伤\n" +
                                "假欢畅 又何妨 无人共享\n" +
                                "你曾经是我的边疆\n" +
                                "抵挡我所有的悲伤\n" +
                                "西风残 故人往\n" +
                                "如今被爱流放\n" +
                                "困 在了眼泪中央\n" +
                                "啊 啊（轻解霓裳 咽泪换笑妆）\n" +
                                "啊 啊（等你戎装 去呼啸沧桑）\n" +
                                "啊（过往终究止不住流淌）\n" +
                                "啊（去御剑飞翔）\n" +
                                "啊（也许会飞出这感伤）\n" +
                                "暮色起看天边斜阳\n" +
                                "夜未央星河独流淌\n" +
                                "天晴朗 好风光\n" +
                                "若你不在身旁\n" +
                                "能 上苍穹又怎样\n" +
                                "船过空港 将寂寞豢养\n" +
                                "旷野霜降 低垂了泪光\n" +
                                "啊（是谁陨落了我的太阳）\n" +
                                "啊（是你的模样）\n" +
                                "啊（带走我所有的光芒）\n" +
                                "扬帆远航 敌不过彷徨\n" +
                                "奈何流放 敌不过苍凉\n" +
                                "啊（我要潜入回忆的汪洋）\n" +
                                "啊（寻你的模样）\n" +
                                "啊（唯有你是我的天堂）\n" +
                                "唯有你是我的 天堂");
                    }
                    if(frag1.name[i+change].equals("如约而至")){
                        textView1.setText("如约而至 - 许嵩\n" +
                                "词：许嵩\n" +
                                "曲：许嵩\n" +
                                "如果早知道那天是我们最后一面\n" +
                                "我绝不会和你谈论琐事浪费时间\n" +
                                "人生里看似偶然却又必经的告别\n" +
                                "无约而至 无人可免\n" +
                                "有一回置身某个似曾相识的场景\n" +
                                "那一刻我忽然感应你就在我身边\n" +
                                "过去 当下 未来未必确有其界限\n" +
                                "或许爱能穿越时间 抵达永远\n" +
                                "等到秋叶终于金黄\n" +
                                "等到华发悄然苍苍\n" +
                                "我们相约老地方\n" +
                                "等到人已不再奔忙\n" +
                                "等到心也不再轻狂\n" +
                                "我们相约老地方\n" +
                                "等到记忆只剩精华\n" +
                                "等到笑容不掺伪装\n" +
                                "我们相约老地方\n" +
                                "等到释怀所有悲伤\n" +
                                "等到体谅世事无常\n" +
                                "我们相约老地方\n" +
                                "有一回置身某个似曾相识的场景\n" +
                                "那一刻我忽然感应你就在我身边\n" +
                                "过去 当下 未来未必确有其界限\n" +
                                "或许爱能穿越时间 抵达永远\n" +
                                "等到秋叶终于金黄\n" +
                                "等到华发悄然苍苍\n" +
                                "我们相约老地方\n" +
                                "等到人已不再奔忙\n" +
                                "等到心也不再轻狂\n" +
                                "我们相约老地方\n" +
                                "等到记忆只剩精华\n" +
                                "等到笑容不掺伪装\n" +
                                "我们相约老地方\n" +
                                "等到释怀所有悲伤\n" +
                                "等到体谅世事无常\n" +
                                "我们相约老地方\n" +
                                "等到记忆只剩精华\n" +
                                "等到笑容不掺伪装\n" +
                                "我们相约老地方\n" +
                                "等到释怀所有悲伤\n" +
                                "等到体谅世事无常\n" +
                                "我们相约老地方");
                    }
                    if(frag1.name[i+change].equals("雨幕")){
                        textView1.setText("雨幕\n"+
                                "桃花岛屿之巅\n" +
                                "一座残碑镇住想念\n" +
                                "故人题词十年前\n" +
                                "而今古苔已横啮\n" +
                                "冬深春浅时节\n" +
                                "岛畔福船入港寒夜\n" +
                                "蒙蒙雾气里跃上甲板\n" +
                                "道声勿念\n" +
                                "海上三日无言 卧听雨打舷\n" +
                                "邻舱客叩门寒暄\n" +
                                "他鹤发童颜 举着夜光杯\n" +
                                "与我对饮舷窗前\n" +
                                "窗外潇潇的雨幕里\n" +
                                "飘然一曲诱我侧耳听\n" +
                                "水面箫中剑的倒影\n" +
                                "是爱中藏恨的诗句\n" +
                                "我从潇潇的雨幕里\n" +
                                "遥望漉雪千山都过尽\n" +
                                "隔海隔山你的背影\n" +
                                "冬深春浅时节\n" +
                                "岛畔福船入港寒夜\n" +
                                "蒙蒙雾气里跃上甲板\n" +
                                "道声勿念\n" +
                                "海上三日无言 卧听雨打舷\n" +
                                "邻舱客叩门寒暄\n" +
                                "他鹤发童颜 举着夜光杯\n" +
                                "与我对饮舷窗前\n" +
                                "窗外潇潇的雨幕里\n" +
                                "飘然一曲诱我侧耳听\n" +
                                "水面箫中剑的倒影\n" +
                                "是爱中藏恨的诗句\n" +
                                "我从潇潇的雨幕里\n" +
                                "遥望漉雪千山都过尽\n" +
                                "隔海隔山你的背影\n" +
                                "窗外潇潇的雨幕里\n" +
                                "飘然一曲诱我侧耳听\n" +
                                "水面箫中剑的倒影\n" +
                                "是爱中藏恨的诗句\n" +
                                "我在潇潇的雨幕里\n" +
                                "漉雪千山都过尽\n" +
                                "隔海隔山你的背影\n" +
                                "绯泊之间喘息渐停");
                    }
                    if(frag1.name[i+change].equals("棠梨煎雪")){
                        textView1.setText("棠梨煎雪\n" +
                                "演唱：银临\n" +
                                "作词：商连\n" +
                                "作曲：灰原穷\n" +
                                "编曲：灰原穷\n" +
                                "混缩：灰原穷\n" +
                                "专辑：腐草为萤\n" +
                                "歌词编辑：友友音悦（罗显友）\n" +
                                "青鲤来时遥闻春溪声声碎\n" +
                                "嗅得手植棠梨初发轻黄蕊\n" +
                                "待小暑悄过 新梨渐垂\n" +
                                "来邀东邻女伴撷果缓缓归\n" +
                                "旧岁采得枝头细雪\n" +
                                "今朝飘落胭脂梨叶\n" +
                                "轻挼草色二三入卷\n" +
                                "细呷春酒淡始觉甜\n" +
                                "依旧是\n" +
                                "偏爱枕惊鸿二字入梦的时节\n" +
                                "烛火惺忪却可与她漫聊彻夜\n" +
                                "早春暮春 酒暖花深\n" +
                                "便好似一生心事只得一人来解\n" +
                                "岁岁花藻檐下共将棠梨煎雪\n" +
                                "自总角至你我某日辗转天边\n" +
                                "天淡天青 宿雨沾襟\n" +
                                "一年一会信笺却只见寥寥数言\n" +
                                "旧岁采得枝头细雪\n" +
                                "今朝飘落胭脂梨叶\n" +
                                "轻挼草色二三入卷\n" +
                                "细呷春酒淡始觉甜\n" +
                                "依旧是\n" +
                                "偏爱枕惊鸿二字入梦的时节\n" +
                                "烛火惺忪却可与她漫聊彻夜\n" +
                                "早春暮春 酒暖花深\n" +
                                "便好似一生心事只得一人来解\n" +
                                "岁岁花藻檐下共将棠梨煎雪\n" +
                                "自总角至你我某日辗转天边\n" +
                                "天淡天青 宿雨沾襟\n" +
                                "一年一会信笺却只见寥寥数言\n" +
                                "雨中灯市欲眠 原已萧萧数年\n" +
                                "似有故人轻叩 再将棠梨煎雪\n" +
                                "能否消得\n" +
                                "你一路而来的半生风雪");
                    }
                    if(frag1.name[i+change].equals("绘笔江南")){
                        textView1.setText("绘笔江南 - 叶洛洛/西瓜JUN/泥鳅Niko/知性的小方块\n" +
                                "词：南岐\n" +
                                "曲：贺鑫\n" +
                                "编曲：糖番茄音乐工作组\n" +
                                "特别鸣谢：胡碧乔\n" +
                                "笛子：徐之博dizi\n" +
                                "分轨混音：Wuli包子\n" +
                                "人声混音：泥鳅Niko\n" +
                                "和声编唱：泥鳅Niko\n" +
                                "曲绘：BlazeK\n" +
                                "PV：不一定\n" +
                                "美工：池崎智央\n" +
                                "泥鳅（唐伯虎）：\n" +
                                "绘一卷花鸟对诗吟\n" +
                                "讨个游春园的意趣\n" +
                                "人间来去 身后如何评\n" +
                                "他人求利禄 此番我随意\n" +
                                "西瓜（文征明）：\n" +
                                "鱼虫山石并杨柳青\n" +
                                "邀山河万物作诗笔\n" +
                                "小山停云 再二三知己\n" +
                                "尽各家风致 笔下才淋漓\n" +
                                "洛洛/方块：\n" +
                                "春宴春酒何不与你共饮\n" +
                                "共赏熏风细软 吹开好光景\n" +
                                "醉极也狂言 世人看分明\n" +
                                "天下文榜自有我才名\n" +
                                "西瓜/泥鳅：\n" +
                                "桃花牡丹一并纳入衣襟\n" +
                                "结交这团簇缤纷江南灵气\n" +
                                "醉极也贪眠 花下半梦半醒\n" +
                                "恍然天地间我留名\n" +
                                "方块（祝枝山）：\n" +
                                "百家诗书百样风流\n" +
                                "风雅平生都卷成册\n" +
                                "饮酒吟诗 也自得其乐\n" +
                                "一梦十九载 凭谁来添色\n" +
                                "洛洛（徐祯卿）：\n" +
                                "无情春秋多情月色\n" +
                                "笔墨下盛妆点山河\n" +
                                "名满士林 虚名赠诗歌\n" +
                                "再看扬州花 摘落不可得\n" +
                                "西瓜/泥鳅：\n" +
                                "春宴春酒何不与你共饮\n" +
                                "共赏熏风细软 吹开好光景\n" +
                                "醉极也狂言 世人看分明\n" +
                                "天下文榜自有我才名\n" +
                                "洛洛/方块：\n" +
                                "桃花牡丹一并纳入衣襟\n" +
                                "结交这团簇缤纷江南灵气\n" +
                                "醉极也贪眠 花下半梦半醒\n" +
                                "恍然天地间我留名\n" +
                                "西瓜：\n" +
                                "青山绿水何处与你共饮\n" +
                                "洛洛：\n" +
                                "共赏芳菲错落 抬眼好光景\n" +
                                "泥鳅：\n" +
                                "人生得意时 不若逢知音\n" +
                                "方块：\n" +
                                "天下文榜自有君才名\n" +
                                "合：\n" +
                                "风月炊烟一并纳入衣襟\n" +
                                "结交这少年人红尘烟火气\n" +
                                "辞别山河去 年岁半梦半醒\n" +
                                "恍然天地间我留名");
                    }
                    if(frag1.name[i+change].equals("恣意千年")){
                        textView1.setText("只有影子、云の泣、封茗囧菌、HITA、Aki阿杰、赵方婧、玄觞、王梓钰 - 瓷意千年\n" +
                                "作词：小玖州\n" +
                                "作曲：李星月\n" +
                                "编曲：王景（零小七）\n" +
                                "和声编写：李星月\n" +
                                "男和声：Jason嘉\n" +
                                "女和声：足音\n" +
                                "后期：王韩一淋\n" +
                                "母带：子棋\n" +
                                "题字：叶巇\n" +
                                "背景画师：叶巇\n" +
                                "美工：曲画鸢\n" +
                                "PV：咔擦一刀\n" +
                                "文案：小玖州\n" +
                                "总策划：Jason嘉\n" +
                                "协助策划：小玖州\n" +
                                "只有影子：\n" +
                                "空山烟雨朦胧\n" +
                                "纸伞晕染了青瞳\n" +
                                "天近晚晴邂逅\n" +
                                "在世间初梦相逢\n" +
                                "云の泣：\n" +
                                "翠色千峰\n" +
                                "越泥玉玲珑\n" +
                                "臣庶传 王孙醉\n" +
                                "赠一折清风\n" +
                                "封茗囧菌：\n" +
                                "雪落孤城惊鸿\n" +
                                "嫩荷涵露似瑶宫\n" +
                                "箸击山水妙空\n" +
                                "闻莲动亦或心动\n" +
                                "Hita：\n" +
                                "跨越过岁月山河\n" +
                                "兼程相送\n" +
                                "予你风情万种\n" +
                                "只有影子+云の泣：\n" +
                                "赏过灯火阑珊处\n" +
                                "轻舞慢 杏花眼\n" +
                                "吟过唐词三百篇\n" +
                                "迁客赴 共婵娟\n" +
                                "封茗囧菌+Hita：\n" +
                                "铺纸提写 香得永年\n" +
                                "春秋换来 红泥一点\n" +
                                "只有影子+Hita：\n" +
                                "陶舍雕花素手纤\n" +
                                "水中莲 馨香传\n" +
                                "云の泣+封茗囧菌：\n" +
                                "一寸相思复开轩\n" +
                                "渔家火 照不眠\n" +
                                "只有影子+云の泣+封茗囧菌+Hita：\n" +
                                "黛眼初见\n" +
                                "清风有情托付琴弦\n" +
                                "足音：\n" +
                                "缤纷落满春源桃\n" +
                                "陌上早 知多少\n" +
                                "杨柳外乡俚歌谣\n" +
                                "林中鸟 向云霄\n" +
                                "雨打芭蕉 风声轻巧\n" +
                                "谁家笙箫 歌尽窈窕\n" +
                                "她又藏在 哪座家窑\n" +
                                "炊烟无人敢惊扰\n" +
                                "马儿跑 鱼儿摇\n" +
                                "世外三两醉今宵\n" +
                                "担中挑 画里瞧\n" +
                                "千古难描\n" +
                                "枕青石明月松间照\n" +
                                "烟花里笑偕老偕老\n" +
                                "Aki阿杰：\n" +
                                "玉壶春江贺送\n" +
                                "窑藏龙泉敬白首\n" +
                                "素意浑然天成\n" +
                                "雕卷帘西风消瘦\n" +
                                "赵方婧：\n" +
                                "掷笔江流 水墨作星宿\n" +
                                "百花嗅 艳芳斗\n" +
                                "自添为不朽\n" +
                                "玄觞：\n" +
                                "祥瑞齐天来寿\n" +
                                "伴行舟鸳鸯同游\n" +
                                "秀色不染通透\n" +
                                "再勾勒行云淡愁\n" +
                                "王梓钰：\n" +
                                "匠心借山水缠枝\n" +
                                "绘入薄釉\n" +
                                "勾画惹风流\n" +
                                "Aki阿杰+赵方婧：\n" +
                                "赏过灯火阑珊处\n" +
                                "轻舞慢 杏花眼\n" +
                                "只有影子+赵方婧：\n" +
                                "吟过唐词三百篇\n" +
                                "迁客赴 共婵娟\n" +
                                "玄觞+王梓钰：\n" +
                                "铺纸提写 香得永年\n" +
                                "春秋换来 红泥一点\n" +
                                "Aki阿杰+王梓钰：\n" +
                                "陶舍雕花素手纤\n" +
                                "水中莲 馨香传\n" +
                                "赵方婧+玄觞：\n" +
                                "一寸相思复开轩\n" +
                                "渔家火 照不眠\n" +
                                "Aki阿杰+赵方婧+玄觞+王梓钰：\n" +
                                "黛眼初见\n" +
                                "清风有情托付琴弦\n" +
                                "只有影子+云の泣+封茗囧菌+Hita：\n" +
                                "松声云影自壶天\n" +
                                "丹青卷 九曲泉\n" +
                                "雕龙描凤别人间\n" +
                                "万般皆 青山携\n" +
                                "只有影子+封茗囧菌：\n" +
                                "釉蘸漪涟 刀销圆月\n" +
                                "云の泣+Hita：\n" +
                                "山水带竹 百花尽现\n" +
                                "Aki阿杰+赵方婧+玄觞+王梓钰：\n" +
                                "卧听江城一夜雪\n" +
                                "且吟酌 思华年\n" +
                                "细搅墨香入筠笼\n" +
                                "空樽前 绕绿烟\n" +
                                "合：\n" +
                                "兰斛香眠\n" +
                                "把盏敬谢盛世千年\n" +
                                "只有影子+云の泣：\n" +
                                "缥沫弄颜 沧桑添\n" +
                                "封茗囧菌+Hita：\n" +
                                "雨过天青 深巷斜\n" +
                                "Aki阿杰+赵方婧：\n" +
                                "知音闻弦 风月无边\n" +
                                "玄觞+王梓钰：\n" +
                                "世代相传静谧人间\n" +
                                "瓷意千年");
                    }
                    if(frag1.name[i+change].equals("黯然销魂")){
                        textView1.setText("玄觞 - 黯然销魂\n" +
                                "作词：玄觞\n" +
                                "作曲：玄觞\n" +
                                "枝头薄雪尚存\n" +
                                "远山传来暮鼓声声\n" +
                                "斜阳散了余温\n" +
                                "凉意入骨几寸\n" +
                                "渡口半盏摇灯\n" +
                                "经历多少日落月升\n" +
                                "流水脉脉淌过世事浮尘\n" +
                                "向来风花雪月动人\n" +
                                "相偎桃林醉闻晚风\n" +
                                "抚琴对饮仗剑红尘\n" +
                                "冬去春来寒暑几更\n" +
                                "情深一寸伤多一分\n" +
                                "如梦江湖如戏人生是我沉沦\n" +
                                "桃花绯雨故城荡着烟波\n" +
                                "十里纷纷\n" +
                                "疏影斜月河灯湖畔悠悠琴声\n" +
                                "仿若昨夜星辰随风摇落\n" +
                                "弦月微冷\n" +
                                "浮光掠影最是黯然销魂\n" +
                                "从来风花雪月动人\n" +
                                "千里雨霁水天难分\n" +
                                "若说最美不过重逢\n" +
                                "他日久别偶遇故人\n" +
                                "话说三分痛却十分\n" +
                                "与你错失太多前尘\n" +
                                "嗯哼\n" +
                                "从来风花雪月动人\n" +
                                "千里雨霁水天难分\n" +
                                "最美不过重逢\n" +
                                "他日久别偶遇故人\n" +
                                "话说三分\n" +
                                "啊\n" +
                                "与你错失太多前尘\n" +
                                "哦\n" +
                                "如梦江湖如戏人生是我沉沦");
                    }
                    if(frag1.name[i+change].equals("千梦")){
                        textView1.setText("双笙、玄觞 - 千梦\n" +
                                "作词：原晞\n" +
                                "作曲：灰原穷\n" +
                                "画一笔\n" +
                                "炊烟十里\n" +
                                "依偎着\n" +
                                "人间朝夕\n" +
                                "故事中\n" +
                                "原来是你\n" +
                                "一生山水为迹\n" +
                                "有桃之夭夭\n" +
                                "凌风过浮生缥缈\n" +
                                "回旋年少\n" +
                                "不舍唱完的歌谣\n" +
                                "你眉眼含笑\n" +
                                "我亦多情不敢老\n" +
                                "彼时光景正好\n" +
                                "不见归来\n" +
                                "相思寄于山海\n" +
                                "风在城外\n" +
                                "裁去春秋几载\n" +
                                "似曾是桥上轻雨伞开\n" +
                                "你着一袭素白\n" +
                                "回首千千对影燕徘徊\n" +
                                "借一缕\n" +
                                "月色如洗\n" +
                                "点染成\n" +
                                "相思无题\n" +
                                "曲终后\n" +
                                "依稀是你\n" +
                                "从此天涯为期\n" +
                                "兰舟催发早\n" +
                                "执手在苏堤春晓\n" +
                                "岁岁燕丘\n" +
                                "策马天下知多少\n" +
                                "听雨花事了\n" +
                                "春风却莫观其妙\n" +
                                "彼时光景正好\n" +
                                "不见归来\n" +
                                "相思寄于山海\n" +
                                "风在城外\n" +
                                "裁去春秋几载\n" +
                                "似曾是桥上轻雨伞开\n" +
                                "你着一袭素白\n" +
                                "回首千千对影燕徘徊\n" +
                                "举杯邀月\n" +
                                "醉在桃溪春野\n" +
                                "南海飞雪\n" +
                                "吻过你的眉睫\n" +
                                "想那年树下回眸无邪\n" +
                                "恰逢花雨未歇\n" +
                                "一梦千千让光阴重叠");
                    }
                    if(frag1.name[i+change].equals("ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)")){
                        textView1.setText("BLACKPINK - AS IF IT'S YOUR LAST(Japan Version BLACKPINK 2019-2020 WORLD TOUR IN YOUR AREA -TOKYO DOME-)\n" +
                                "作词：TEDDY、BrotherSu、CHOICE37、SUNNY BOY\n" +
                                "作曲：TEDDY、FUTURE BOUNCE、Lydia Paek\n" +
                                "编曲：FUTURE BOUNCE、TEDDY\n" +
                                "Little bit of that\n" +
                                "Little bit of this\n" +
                                "Little bit of that\n" +
                                "Gimme gimme gimme\n" +
                                "Gimme gimme gimme\n" +
                                "Gimme little bit of that\n" +
                                "あなたしか見えない\n" +
                                "ドキドキしちゃうわ\n" +
                                "赤らむ顔胸の中も\n" +
                                "思い通りにいかないの\n" +
                                "ひと握りの砂のよう\n" +
                                "まるであなたは\n" +
                                "手ごわいほど\n" +
                                "惹かれるもっと\n" +
                                "思い通りにいかないの\n" +
                                "私の吐息を感じてほしい\n" +
                                "見つめていても missing you\n" +
                                "だから won't you set me free\n" +
                                "Baby もっと\n" +
                                "ぎゅっと強くずっと\n" +
                                "抱きしめて何もいらないの\n" +
                                "素直に今キスをしてほしい\n" +
                                "最後の愛のように\n" +
                                "最後のよう最後のよう\n" +
                                "最後の夜のよう love\n" +
                                "最後のよう最後のよう\n" +
                                "明日がこないよう love\n" +
                                "Uh I'ma fall in love baby\n" +
                                "You gon finna catch me\n" +
                                "Uh give you all of this baby\n" +
                                "Call me pretty and nasty\n" +
                                "Cause we gonna get it my love you can bet it on\n" +
                                "Black we gon double the stack on them whoa\n" +
                                "I be the Bonnie and you be my Clyde\n" +
                                "We ride or die\n" +
                                "X's and O's\n" +
                                "時間が過ぎても焦る未だに\n" +
                                "あなたが全てよ missing you\n" +
                                "だから won't you set me free\n" +
                                "Baby もっと\n" +
                                "ぎゅっと強くずっと\n" +
                                "抱きしめて何もいらないの\n" +
                                "素直に今キスをしてほしい\n" +
                                "最後の愛のように\n" +
                                "最後のよう最後のよう\n" +
                                "最後の夜のよう love\n" +
                                "最後のよう最後のよう\n" +
                                "明日がこないよう love\n" +
                                "1 2 3から始めるわ\n" +
                                "決して後ろ振り向かないわ\n" +
                                "私の全てを受け止めてよ\n" +
                                "誰にも邪魔させないわ\n" +
                                "BLACKPINK in your area\n" +
                                "Baby もっと\n" +
                                "ぎゅっと強くずっと\n" +
                                "抱きしめて何もいらないの\n" +
                                "素直に今キスをしてほしい\n" +
                                "最後の愛のように\n" +
                                "最後のよう最後のよう\n" +
                                "最後の夜のよう love\n" +
                                "最後のよう最後のよう\n" +
                                "明日がこないよう love");
                    }
                    musicControl.play(i+change);
                    animator.start();
                    break;
                }
            case R.id.btn_next://播放下一首
                if((i + change) == 10) {//这里musicName.length-1表示的最后一首歌的下标
                    Toast.makeText(Music_Activity.this, "已经是最后一首了", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    change++;
                    musicbutton.setImageResource(R.drawable.bofang);
                    music_pic.setImageResource(frag1.icons[i+change]);
                    name_song.setText(frag1.name[i+change]);
                    if(frag1.name[i+change].equals("双笙 - 故梦")){
                        textView1.setText("故梦 - 伦桑\n" +
                                "旧忆就像一扇窗\n" +
                                "推开了就再难合上\n" +
                                "谁踩过枯枝轻响\n" +
                                "萤火绘着画屏香\n" +
                                "为谁拢一袖芬芳\n" +
                                "红叶的信笺情意绵长\n" +
                                "他说就这样去流浪\n" +
                                "到美丽的地方\n" +
                                "谁的歌声轻轻\n" +
                                "轻轻唱\n" +
                                "谁的泪水静静淌\n" +
                                "那些年华都付作过往\n" +
                                "他们偎依着彼此说好要面对风浪\n" +
                                "又是一地枯黄\n" +
                                "枫叶红了满面秋霜\n" +
                                "这场故梦里\n" +
                                "人生如戏唱\n" +
                                "还有谁登场\n" +
                                "昏黄烛火轻摇晃\n" +
                                "大红盖头下谁彷徨\n" +
                                "流泪的花和荣喜堂\n" +
                                "静静放一旁\n" +
                                "回忆像默片播放\n" +
                                "刻下一寸一寸旧时光\n" +
                                "他说就这样去流浪\n" +
                                "到美丽的地方\n" +
                                "谁的歌声轻轻\n" +
                                "轻轻唱\n" +
                                "谁的泪水静静淌\n" +
                                "愿化一双鸟儿去飞翔\n" +
                                "任身后哭号嘶喊着也追不上\n" +
                                "又一年七月半晚风凉\n" +
                                "斜阳渐矮只影长\n" +
                                "这场故梦里\n" +
                                "孤桨声远荡\n" +
                                "去他乡遗忘");}
                    if(frag1.name[i+change].equals("GAI周延 _ 刘煜 - 沧海一声笑")){
                        textView1.setText("沧海一声笑 (Live) - GAI\n" +
                                "词：黄霑\n" +
                                "曲：黄霑\n" +
                                "原唱：许冠杰/黄霑/罗大佑/徐克\n" +
                                "Rap词：周延GAI/临渡\n" +
                                "编曲：宋阳\n" +
                                "Program：宋阳\n" +
                                "定位制作人：刘洲\n" +
                                "琵琶：李琼\n" +
                                "中国大鼓：刘效松（乐队）/郑瑀\n" +
                                "沧海一声笑\n" +
                                "滔滔两岸潮\n" +
                                "浮沉随浪 记今朝\n" +
                                "苍天笑 纷纷世上潮\n" +
                                "谁负谁胜出天知晓\n" +
                                "何处 笙箫\n" +
                                "琴声 入鞘\n" +
                                "我以黄河水 蘸墨挥毫\n" +
                                "可路正遥 今生如刀\n" +
                                "若一晌喝醉看我未老\n" +
                                "要海阔天高\n" +
                                "高声大笑\n" +
                                "笑过勾销\n" +
                                "恩怨 妄念 贪恋 再逍遥\n" +
                                "翻开你人生的简历\n" +
                                "发现你啥子都没得\n" +
                                "该如何看待你自己\n" +
                                "时间该咋个切支配\n" +
                                "是哪些人混不下去\n" +
                                "土豆吃起去下力\n" +
                                "选哪条路都不好走\n" +
                                "啥子命我都不抗拒\n" +
                                "我命硬学不来弯腰\n" +
                                "一山还要比一山高\n" +
                                "沧海一声笑 滔滔两岸潮\n" +
                                "浮沉随浪 记今朝\n" +
                                "苍天笑 纷纷世上潮\n" +
                                "谁负谁胜出天知晓\n" +
                                "天 地 人 和\n" +
                                "恩怨似云烟转眼飞很远\n" +
                                "早看破红尘\n" +
                                "也不与世人周旋\n" +
                                "逍遥的侠客\n" +
                                "要踏上江湖归路\n" +
                                "坐禅或修行\n" +
                                "不枉此生虚度\n" +
                                "What we do like them\n" +
                                "他们抓不住感觉\n" +
                                "盖九天揽月写广陵散诀\n" +
                                "孩子还在勤工俭学\n" +
                                "闪耀的钻石不会埋没\n" +
                                "走一遭发现从没来过\n" +
                                "到处招摇过市在浑天过日\n" +
                                "歌词唱到口吐白沫\n" +
                                "我命硬学不来弯腰\n" +
                                "一山还要比一山高\n" +
                                "沧海一声笑 滔滔两岸潮\n" +
                                "浮沉随浪 记今朝\n" +
                                "江山笑 烟雨遥\n" +
                                "涛浪淘尽红尘世俗知多少\n" +
                                "清风笑 竟惹寂寥\n" +
                                "豪情还剩 衣襟晚照\n" +
                                "豪情还剩 衣襟晚照");}
                    if(frag1.name[i+change].equals("爱殇")){
                        textView1.setText("爱殇\n" +
                                "暮色起看天边斜阳\n" +
                                "恍惚想起你的脸庞\n" +
                                "毕竟回想 难免徒增感伤\n" +
                                "轻叹息 我们那些好时光\n" +
                                "夜未央繁星落眼眶\n" +
                                "拾一段柔软的光芒\n" +
                                "清风过 曳烛光\n" +
                                "独舞无人欣赏\n" +
                                "留 花瓣随风飘荡\n" +
                                "我要将过往都储藏\n" +
                                "编一段美好的梦想\n" +
                                "也许幻想 到最后会更伤\n" +
                                "假欢畅 又何妨 无人共享\n" +
                                "你曾经是我的边疆\n" +
                                "抵挡我所有的悲伤\n" +
                                "西风残 故人往\n" +
                                "如今被爱流放\n" +
                                "困 在了眼泪中央\n" +
                                "啊 啊（轻解霓裳 咽泪换笑妆）\n" +
                                "啊 啊（等你戎装 去呼啸沧桑）\n" +
                                "啊（过往终究止不住流淌）\n" +
                                "啊（去御剑飞翔）\n" +
                                "啊（也许会飞出这感伤）\n" +
                                "暮色起看天边斜阳\n" +
                                "夜未央星河独流淌\n" +
                                "天晴朗 好风光\n" +
                                "若你不在身旁\n" +
                                "能 上苍穹又怎样\n" +
                                "船过空港 将寂寞豢养\n" +
                                "旷野霜降 低垂了泪光\n" +
                                "啊（是谁陨落了我的太阳）\n" +
                                "啊（是你的模样）\n" +
                                "啊（带走我所有的光芒）\n" +
                                "扬帆远航 敌不过彷徨\n" +
                                "奈何流放 敌不过苍凉\n" +
                                "啊（我要潜入回忆的汪洋）\n" +
                                "啊（寻你的模样）\n" +
                                "啊（唯有你是我的天堂）\n" +
                                "唯有你是我的 天堂");
                    }
                    if(frag1.name[i+change].equals("如约而至")){
                        textView1.setText("如约而至 - 许嵩\n" +
                                "词：许嵩\n" +
                                "曲：许嵩\n" +
                                "如果早知道那天是我们最后一面\n" +
                                "我绝不会和你谈论琐事浪费时间\n" +
                                "人生里看似偶然却又必经的告别\n" +
                                "无约而至 无人可免\n" +
                                "有一回置身某个似曾相识的场景\n" +
                                "那一刻我忽然感应你就在我身边\n" +
                                "过去 当下 未来未必确有其界限\n" +
                                "或许爱能穿越时间 抵达永远\n" +
                                "等到秋叶终于金黄\n" +
                                "等到华发悄然苍苍\n" +
                                "我们相约老地方\n" +
                                "等到人已不再奔忙\n" +
                                "等到心也不再轻狂\n" +
                                "我们相约老地方\n" +
                                "等到记忆只剩精华\n" +
                                "等到笑容不掺伪装\n" +
                                "我们相约老地方\n" +
                                "等到释怀所有悲伤\n" +
                                "等到体谅世事无常\n" +
                                "我们相约老地方\n" +
                                "有一回置身某个似曾相识的场景\n" +
                                "那一刻我忽然感应你就在我身边\n" +
                                "过去 当下 未来未必确有其界限\n" +
                                "或许爱能穿越时间 抵达永远\n" +
                                "等到秋叶终于金黄\n" +
                                "等到华发悄然苍苍\n" +
                                "我们相约老地方\n" +
                                "等到人已不再奔忙\n" +
                                "等到心也不再轻狂\n" +
                                "我们相约老地方\n" +
                                "等到记忆只剩精华\n" +
                                "等到笑容不掺伪装\n" +
                                "我们相约老地方\n" +
                                "等到释怀所有悲伤\n" +
                                "等到体谅世事无常\n" +
                                "我们相约老地方\n" +
                                "等到记忆只剩精华\n" +
                                "等到笑容不掺伪装\n" +
                                "我们相约老地方\n" +
                                "等到释怀所有悲伤\n" +
                                "等到体谅世事无常\n" +
                                "我们相约老地方");
                    }
                    if(frag1.name[i+change].equals("雨幕")){
                        textView1.setText("雨幕\n"+
                                "桃花岛屿之巅\n" +
                                "一座残碑镇住想念\n" +
                                "故人题词十年前\n" +
                                "而今古苔已横啮\n" +
                                "冬深春浅时节\n" +
                                "岛畔福船入港寒夜\n" +
                                "蒙蒙雾气里跃上甲板\n" +
                                "道声勿念\n" +
                                "海上三日无言 卧听雨打舷\n" +
                                "邻舱客叩门寒暄\n" +
                                "他鹤发童颜 举着夜光杯\n" +
                                "与我对饮舷窗前\n" +
                                "窗外潇潇的雨幕里\n" +
                                "飘然一曲诱我侧耳听\n" +
                                "水面箫中剑的倒影\n" +
                                "是爱中藏恨的诗句\n" +
                                "我从潇潇的雨幕里\n" +
                                "遥望漉雪千山都过尽\n" +
                                "隔海隔山你的背影\n" +
                                "冬深春浅时节\n" +
                                "岛畔福船入港寒夜\n" +
                                "蒙蒙雾气里跃上甲板\n" +
                                "道声勿念\n" +
                                "海上三日无言 卧听雨打舷\n" +
                                "邻舱客叩门寒暄\n" +
                                "他鹤发童颜 举着夜光杯\n" +
                                "与我对饮舷窗前\n" +
                                "窗外潇潇的雨幕里\n" +
                                "飘然一曲诱我侧耳听\n" +
                                "水面箫中剑的倒影\n" +
                                "是爱中藏恨的诗句\n" +
                                "我从潇潇的雨幕里\n" +
                                "遥望漉雪千山都过尽\n" +
                                "隔海隔山你的背影\n" +
                                "窗外潇潇的雨幕里\n" +
                                "飘然一曲诱我侧耳听\n" +
                                "水面箫中剑的倒影\n" +
                                "是爱中藏恨的诗句\n" +
                                "我在潇潇的雨幕里\n" +
                                "漉雪千山都过尽\n" +
                                "隔海隔山你的背影\n" +
                                "绯泊之间喘息渐停");
                    }
                    if(frag1.name[i+change].equals("棠梨煎雪")){
                        textView1.setText("棠梨煎雪\n" +
                                "演唱：银临\n" +
                                "作词：商连\n" +
                                "作曲：灰原穷\n" +
                                "编曲：灰原穷\n" +
                                "混缩：灰原穷\n" +
                                "专辑：腐草为萤\n" +
                                "歌词编辑：友友音悦（罗显友）\n" +
                                "青鲤来时遥闻春溪声声碎\n" +
                                "嗅得手植棠梨初发轻黄蕊\n" +
                                "待小暑悄过 新梨渐垂\n" +
                                "来邀东邻女伴撷果缓缓归\n" +
                                "旧岁采得枝头细雪\n" +
                                "今朝飘落胭脂梨叶\n" +
                                "轻挼草色二三入卷\n" +
                                "细呷春酒淡始觉甜\n" +
                                "依旧是\n" +
                                "偏爱枕惊鸿二字入梦的时节\n" +
                                "烛火惺忪却可与她漫聊彻夜\n" +
                                "早春暮春 酒暖花深\n" +
                                "便好似一生心事只得一人来解\n" +
                                "岁岁花藻檐下共将棠梨煎雪\n" +
                                "自总角至你我某日辗转天边\n" +
                                "天淡天青 宿雨沾襟\n" +
                                "一年一会信笺却只见寥寥数言\n" +
                                "旧岁采得枝头细雪\n" +
                                "今朝飘落胭脂梨叶\n" +
                                "轻挼草色二三入卷\n" +
                                "细呷春酒淡始觉甜\n" +
                                "依旧是\n" +
                                "偏爱枕惊鸿二字入梦的时节\n" +
                                "烛火惺忪却可与她漫聊彻夜\n" +
                                "早春暮春 酒暖花深\n" +
                                "便好似一生心事只得一人来解\n" +
                                "岁岁花藻檐下共将棠梨煎雪\n" +
                                "自总角至你我某日辗转天边\n" +
                                "天淡天青 宿雨沾襟\n" +
                                "一年一会信笺却只见寥寥数言\n" +
                                "雨中灯市欲眠 原已萧萧数年\n" +
                                "似有故人轻叩 再将棠梨煎雪\n" +
                                "能否消得\n" +
                                "你一路而来的半生风雪");
                    }
                    if(frag1.name[i+change].equals("绘笔江南")){
                        textView1.setText("绘笔江南 - 叶洛洛/西瓜JUN/泥鳅Niko/知性的小方块\n" +
                                "词：南岐\n" +
                                "曲：贺鑫\n" +
                                "编曲：糖番茄音乐工作组\n" +
                                "特别鸣谢：胡碧乔\n" +
                                "笛子：徐之博dizi\n" +
                                "分轨混音：Wuli包子\n" +
                                "人声混音：泥鳅Niko\n" +
                                "和声编唱：泥鳅Niko\n" +
                                "曲绘：BlazeK\n" +
                                "PV：不一定\n" +
                                "美工：池崎智央\n" +
                                "泥鳅（唐伯虎）：\n" +
                                "绘一卷花鸟对诗吟\n" +
                                "讨个游春园的意趣\n" +
                                "人间来去 身后如何评\n" +
                                "他人求利禄 此番我随意\n" +
                                "西瓜（文征明）：\n" +
                                "鱼虫山石并杨柳青\n" +
                                "邀山河万物作诗笔\n" +
                                "小山停云 再二三知己\n" +
                                "尽各家风致 笔下才淋漓\n" +
                                "洛洛/方块：\n" +
                                "春宴春酒何不与你共饮\n" +
                                "共赏熏风细软 吹开好光景\n" +
                                "醉极也狂言 世人看分明\n" +
                                "天下文榜自有我才名\n" +
                                "西瓜/泥鳅：\n" +
                                "桃花牡丹一并纳入衣襟\n" +
                                "结交这团簇缤纷江南灵气\n" +
                                "醉极也贪眠 花下半梦半醒\n" +
                                "恍然天地间我留名\n" +
                                "方块（祝枝山）：\n" +
                                "百家诗书百样风流\n" +
                                "风雅平生都卷成册\n" +
                                "饮酒吟诗 也自得其乐\n" +
                                "一梦十九载 凭谁来添色\n" +
                                "洛洛（徐祯卿）：\n" +
                                "无情春秋多情月色\n" +
                                "笔墨下盛妆点山河\n" +
                                "名满士林 虚名赠诗歌\n" +
                                "再看扬州花 摘落不可得\n" +
                                "西瓜/泥鳅：\n" +
                                "春宴春酒何不与你共饮\n" +
                                "共赏熏风细软 吹开好光景\n" +
                                "醉极也狂言 世人看分明\n" +
                                "天下文榜自有我才名\n" +
                                "洛洛/方块：\n" +
                                "桃花牡丹一并纳入衣襟\n" +
                                "结交这团簇缤纷江南灵气\n" +
                                "醉极也贪眠 花下半梦半醒\n" +
                                "恍然天地间我留名\n" +
                                "西瓜：\n" +
                                "青山绿水何处与你共饮\n" +
                                "洛洛：\n" +
                                "共赏芳菲错落 抬眼好光景\n" +
                                "泥鳅：\n" +
                                "人生得意时 不若逢知音\n" +
                                "方块：\n" +
                                "天下文榜自有君才名\n" +
                                "合：\n" +
                                "风月炊烟一并纳入衣襟\n" +
                                "结交这少年人红尘烟火气\n" +
                                "辞别山河去 年岁半梦半醒\n" +
                                "恍然天地间我留名");
                    }
                    if(frag1.name[i+change].equals("恣意千年")){
                        textView1.setText("只有影子、云の泣、封茗囧菌、HITA、Aki阿杰、赵方婧、玄觞、王梓钰 - 瓷意千年\n" +
                                "作词：小玖州\n" +
                                "作曲：李星月\n" +
                                "编曲：王景（零小七）\n" +
                                "和声编写：李星月\n" +
                                "男和声：Jason嘉\n" +
                                "女和声：足音\n" +
                                "后期：王韩一淋\n" +
                                "母带：子棋\n" +
                                "题字：叶巇\n" +
                                "背景画师：叶巇\n" +
                                "美工：曲画鸢\n" +
                                "PV：咔擦一刀\n" +
                                "文案：小玖州\n" +
                                "总策划：Jason嘉\n" +
                                "协助策划：小玖州\n" +
                                "只有影子：\n" +
                                "空山烟雨朦胧\n" +
                                "纸伞晕染了青瞳\n" +
                                "天近晚晴邂逅\n" +
                                "在世间初梦相逢\n" +
                                "云の泣：\n" +
                                "翠色千峰\n" +
                                "越泥玉玲珑\n" +
                                "臣庶传 王孙醉\n" +
                                "赠一折清风\n" +
                                "封茗囧菌：\n" +
                                "雪落孤城惊鸿\n" +
                                "嫩荷涵露似瑶宫\n" +
                                "箸击山水妙空\n" +
                                "闻莲动亦或心动\n" +
                                "Hita：\n" +
                                "跨越过岁月山河\n" +
                                "兼程相送\n" +
                                "予你风情万种\n" +
                                "只有影子+云の泣：\n" +
                                "赏过灯火阑珊处\n" +
                                "轻舞慢 杏花眼\n" +
                                "吟过唐词三百篇\n" +
                                "迁客赴 共婵娟\n" +
                                "封茗囧菌+Hita：\n" +
                                "铺纸提写 香得永年\n" +
                                "春秋换来 红泥一点\n" +
                                "只有影子+Hita：\n" +
                                "陶舍雕花素手纤\n" +
                                "水中莲 馨香传\n" +
                                "云の泣+封茗囧菌：\n" +
                                "一寸相思复开轩\n" +
                                "渔家火 照不眠\n" +
                                "只有影子+云の泣+封茗囧菌+Hita：\n" +
                                "黛眼初见\n" +
                                "清风有情托付琴弦\n" +
                                "足音：\n" +
                                "缤纷落满春源桃\n" +
                                "陌上早 知多少\n" +
                                "杨柳外乡俚歌谣\n" +
                                "林中鸟 向云霄\n" +
                                "雨打芭蕉 风声轻巧\n" +
                                "谁家笙箫 歌尽窈窕\n" +
                                "她又藏在 哪座家窑\n" +
                                "炊烟无人敢惊扰\n" +
                                "马儿跑 鱼儿摇\n" +
                                "世外三两醉今宵\n" +
                                "担中挑 画里瞧\n" +
                                "千古难描\n" +
                                "枕青石明月松间照\n" +
                                "烟花里笑偕老偕老\n" +
                                "Aki阿杰：\n" +
                                "玉壶春江贺送\n" +
                                "窑藏龙泉敬白首\n" +
                                "素意浑然天成\n" +
                                "雕卷帘西风消瘦\n" +
                                "赵方婧：\n" +
                                "掷笔江流 水墨作星宿\n" +
                                "百花嗅 艳芳斗\n" +
                                "自添为不朽\n" +
                                "玄觞：\n" +
                                "祥瑞齐天来寿\n" +
                                "伴行舟鸳鸯同游\n" +
                                "秀色不染通透\n" +
                                "再勾勒行云淡愁\n" +
                                "王梓钰：\n" +
                                "匠心借山水缠枝\n" +
                                "绘入薄釉\n" +
                                "勾画惹风流\n" +
                                "Aki阿杰+赵方婧：\n" +
                                "赏过灯火阑珊处\n" +
                                "轻舞慢 杏花眼\n" +
                                "只有影子+赵方婧：\n" +
                                "吟过唐词三百篇\n" +
                                "迁客赴 共婵娟\n" +
                                "玄觞+王梓钰：\n" +
                                "铺纸提写 香得永年\n" +
                                "春秋换来 红泥一点\n" +
                                "Aki阿杰+王梓钰：\n" +
                                "陶舍雕花素手纤\n" +
                                "水中莲 馨香传\n" +
                                "赵方婧+玄觞：\n" +
                                "一寸相思复开轩\n" +
                                "渔家火 照不眠\n" +
                                "Aki阿杰+赵方婧+玄觞+王梓钰：\n" +
                                "黛眼初见\n" +
                                "清风有情托付琴弦\n" +
                                "只有影子+云の泣+封茗囧菌+Hita：\n" +
                                "松声云影自壶天\n" +
                                "丹青卷 九曲泉\n" +
                                "雕龙描凤别人间\n" +
                                "万般皆 青山携\n" +
                                "只有影子+封茗囧菌：\n" +
                                "釉蘸漪涟 刀销圆月\n" +
                                "云の泣+Hita：\n" +
                                "山水带竹 百花尽现\n" +
                                "Aki阿杰+赵方婧+玄觞+王梓钰：\n" +
                                "卧听江城一夜雪\n" +
                                "且吟酌 思华年\n" +
                                "细搅墨香入筠笼\n" +
                                "空樽前 绕绿烟\n" +
                                "合：\n" +
                                "兰斛香眠\n" +
                                "把盏敬谢盛世千年\n" +
                                "只有影子+云の泣：\n" +
                                "缥沫弄颜 沧桑添\n" +
                                "封茗囧菌+Hita：\n" +
                                "雨过天青 深巷斜\n" +
                                "Aki阿杰+赵方婧：\n" +
                                "知音闻弦 风月无边\n" +
                                "玄觞+王梓钰：\n" +
                                "世代相传静谧人间\n" +
                                "瓷意千年");
                    }
                    if(frag1.name[i+change].equals("黯然销魂")){
                        textView1.setText("玄觞 - 黯然销魂\n" +
                                "作词：玄觞\n" +
                                "作曲：玄觞\n" +
                                "枝头薄雪尚存\n" +
                                "远山传来暮鼓声声\n" +
                                "斜阳散了余温\n" +
                                "凉意入骨几寸\n" +
                                "渡口半盏摇灯\n" +
                                "经历多少日落月升\n" +
                                "流水脉脉淌过世事浮尘\n" +
                                "向来风花雪月动人\n" +
                                "相偎桃林醉闻晚风\n" +
                                "抚琴对饮仗剑红尘\n" +
                                "冬去春来寒暑几更\n" +
                                "情深一寸伤多一分\n" +
                                "如梦江湖如戏人生是我沉沦\n" +
                                "桃花绯雨故城荡着烟波\n" +
                                "十里纷纷\n" +
                                "疏影斜月河灯湖畔悠悠琴声\n" +
                                "仿若昨夜星辰随风摇落\n" +
                                "弦月微冷\n" +
                                "浮光掠影最是黯然销魂\n" +
                                "从来风花雪月动人\n" +
                                "千里雨霁水天难分\n" +
                                "若说最美不过重逢\n" +
                                "他日久别偶遇故人\n" +
                                "话说三分痛却十分\n" +
                                "与你错失太多前尘\n" +
                                "嗯哼\n" +
                                "从来风花雪月动人\n" +
                                "千里雨霁水天难分\n" +
                                "最美不过重逢\n" +
                                "他日久别偶遇故人\n" +
                                "话说三分\n" +
                                "啊\n" +
                                "与你错失太多前尘\n" +
                                "哦\n" +
                                "如梦江湖如戏人生是我沉沦");
                    }
                    if(frag1.name[i+change].equals("千梦")){
                        textView1.setText("双笙、玄觞 - 千梦\n" +
                                "作词：原晞\n" +
                                "作曲：灰原穷\n" +
                                "画一笔\n" +
                                "炊烟十里\n" +
                                "依偎着\n" +
                                "人间朝夕\n" +
                                "故事中\n" +
                                "原来是你\n" +
                                "一生山水为迹\n" +
                                "有桃之夭夭\n" +
                                "凌风过浮生缥缈\n" +
                                "回旋年少\n" +
                                "不舍唱完的歌谣\n" +
                                "你眉眼含笑\n" +
                                "我亦多情不敢老\n" +
                                "彼时光景正好\n" +
                                "不见归来\n" +
                                "相思寄于山海\n" +
                                "风在城外\n" +
                                "裁去春秋几载\n" +
                                "似曾是桥上轻雨伞开\n" +
                                "你着一袭素白\n" +
                                "回首千千对影燕徘徊\n" +
                                "借一缕\n" +
                                "月色如洗\n" +
                                "点染成\n" +
                                "相思无题\n" +
                                "曲终后\n" +
                                "依稀是你\n" +
                                "从此天涯为期\n" +
                                "兰舟催发早\n" +
                                "执手在苏堤春晓\n" +
                                "岁岁燕丘\n" +
                                "策马天下知多少\n" +
                                "听雨花事了\n" +
                                "春风却莫观其妙\n" +
                                "彼时光景正好\n" +
                                "不见归来\n" +
                                "相思寄于山海\n" +
                                "风在城外\n" +
                                "裁去春秋几载\n" +
                                "似曾是桥上轻雨伞开\n" +
                                "你着一袭素白\n" +
                                "回首千千对影燕徘徊\n" +
                                "举杯邀月\n" +
                                "醉在桃溪春野\n" +
                                "南海飞雪\n" +
                                "吻过你的眉睫\n" +
                                "想那年树下回眸无邪\n" +
                                "恰逢花雨未歇\n" +
                                "一梦千千让光阴重叠");
                    }
                    if(frag1.name[i+change].equals("ACKPINK - AS IF IT'S YOUR LAST (마지막처럼)")){
                        textView1.setText("BLACKPINK - AS IF IT'S YOUR LAST(Japan Version BLACKPINK 2019-2020 WORLD TOUR IN YOUR AREA -TOKYO DOME-)\n" +
                                "作词：TEDDY、BrotherSu、CHOICE37、SUNNY BOY\n" +
                                "作曲：TEDDY、FUTURE BOUNCE、Lydia Paek\n" +
                                "编曲：FUTURE BOUNCE、TEDDY\n" +
                                "Little bit of that\n" +
                                "Little bit of this\n" +
                                "Little bit of that\n" +
                                "Gimme gimme gimme\n" +
                                "Gimme gimme gimme\n" +
                                "Gimme little bit of that\n" +
                                "あなたしか見えない\n" +
                                "ドキドキしちゃうわ\n" +
                                "赤らむ顔胸の中も\n" +
                                "思い通りにいかないの\n" +
                                "ひと握りの砂のよう\n" +
                                "まるであなたは\n" +
                                "手ごわいほど\n" +
                                "惹かれるもっと\n" +
                                "思い通りにいかないの\n" +
                                "私の吐息を感じてほしい\n" +
                                "見つめていても missing you\n" +
                                "だから won't you set me free\n" +
                                "Baby もっと\n" +
                                "ぎゅっと強くずっと\n" +
                                "抱きしめて何もいらないの\n" +
                                "素直に今キスをしてほしい\n" +
                                "最後の愛のように\n" +
                                "最後のよう最後のよう\n" +
                                "最後の夜のよう love\n" +
                                "最後のよう最後のよう\n" +
                                "明日がこないよう love\n" +
                                "Uh I'ma fall in love baby\n" +
                                "You gon finna catch me\n" +
                                "Uh give you all of this baby\n" +
                                "Call me pretty and nasty\n" +
                                "Cause we gonna get it my love you can bet it on\n" +
                                "Black we gon double the stack on them whoa\n" +
                                "I be the Bonnie and you be my Clyde\n" +
                                "We ride or die\n" +
                                "X's and O's\n" +
                                "時間が過ぎても焦る未だに\n" +
                                "あなたが全てよ missing you\n" +
                                "だから won't you set me free\n" +
                                "Baby もっと\n" +
                                "ぎゅっと強くずっと\n" +
                                "抱きしめて何もいらないの\n" +
                                "素直に今キスをしてほしい\n" +
                                "最後の愛のように\n" +
                                "最後のよう最後のよう\n" +
                                "最後の夜のよう love\n" +
                                "最後のよう最後のよう\n" +
                                "明日がこないよう love\n" +
                                "1 2 3から始めるわ\n" +
                                "決して後ろ振り向かないわ\n" +
                                "私の全てを受け止めてよ\n" +
                                "誰にも邪魔させないわ\n" +
                                "BLACKPINK in your area\n" +
                                "Baby もっと\n" +
                                "ぎゅっと強くずっと\n" +
                                "抱きしめて何もいらないの\n" +
                                "素直に今キスをしてほしい\n" +
                                "最後の愛のように\n" +
                                "最後のよう最後のよう\n" +
                                "最後の夜のよう love\n" +
                                "最後のよう最後のよう\n" +
                                "明日がこないよう love");
                    }
                    musicControl.play(i+change);
                    animator.start();
                    break;
                }



        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbind(isUnbind);//解绑服务
    }
}

