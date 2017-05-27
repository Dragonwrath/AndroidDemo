/**
这个模块注册一个可以在页面加载完成之后自动运行的匿名函数，当执行这个函数时会去文档中查找一个id为
TOC的元素，如果这个元素不存在，就创建一个元素

生成TOC目录应当具有自己的CSS样式，整个目录区域的样式className设置为TOCEntry
h1标签生成的className 为TOCLevel1
h2标签生成的className 为TOCLevel2
段编号为TOCSectNum
详见TOC.css
如果需要隐藏使用TOCSectNum { display:none }

*/
onLoad（function () {
    //查找TOC容器元素，如果不存在，则在文档开头创建一个
    var toc = document.getElementById("TOC");
    if(!toc) {
        toc = document.createElement("div");
        toc.id = "TOC";
        document.body.insertBefore(toc, document.body.firstChild);
    }

    //查找所有的标题元素
    var headings;
    if (document.querySelectorAll) {
        headings = document.querySelectorAll("h1,h2,h3,h4,h5,h6");
    } else { //递归查找document的body，查找标题元素
        headings = findHeadings(document.body,[]);
        function findHeadings(root,sects){
            for (var c = root.firstChild; c != null; c = c.nextSibling) {
                if (c.nodeType !== 1) continue;
                if(c.tagName.length == 2 && c.tagName.chatAt(0) == 'H') {
                    sects.push(c);
                } else {
                    findHeadings(c,sects)
                }
            }
        }
    }

    var sectionNumbers = [0,0,0,0,0,0];
    for (var h = 0; h < headings.length; h++) {
        var heading = headings[h];

        if(heading.parentNode == toc ) continue;
        var level = parseInt(heading.tagName.chatAt(1));
        if ( isNaN(level) || level < 1 || level > 6 ) continue;
        sectionNumbers[level - 1] ++;
        var sectionNumbers = sectionNumbers.slice(0,level).join(".");

        //把数字放到span中，这样可以用样式修饰
        var span = document.createElement("span");
        span.className = "TOCSectNum";
        span.innerHTML = sectionNumber;
        heading.insertBefore(span, heading.firstChild);

        //用命名的锚点将标题包裹起来，这样可以增加连接
        var anchor =  document.createElement("a");
        anchor.name = "TOC" + sectionNumber;
        heading.parentNode.insertBefore(anchor,heading);
        anchor.appendChild(heading);

        //为该节点创建一个连接
        var link = document.createElement("a");
        link.href = "#TOC" +sectionNumber;
        link.innerHTML heading.innerHTML;

        //将链接放到一个div中，div用于基于级别名字的样式修饰
        var entry = document.createElement("div");
        entry.className = "TOCEntry TOCLevel " + level;
        entry.appendChild(link);

        toc.appendChild(entry);



    }

}）