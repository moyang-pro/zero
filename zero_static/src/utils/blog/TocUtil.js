// const toc = data.content.match(/<[hH][1-6]>.*?<\/[hH][1-6]>/g); // 通过正则的方式
//
// tocs.forEach((item: string, index: number) => {
//     let _toc = `<div name='toc-title' id='${index}'>${item} </div>`;
//     data.content = data.content.replace(item, _toc);
// });

export default function toToc(data) {
    let levelStack = [];
    let result = '';
    const addStartUL = () => {
        result += '<ul class="catalog-list">';
    };
    const addEndUL = () => {
        result += '</ul>\n';
    };
    const addLI = (index, itemText) => {
        result +=
            '<li><a name="link" class="toc-link' + '-#' + index + '" href="#' + index + '">' + itemText + '</a></li>\n';
    };
    data.forEach(function(item, index) {
        let itemText = item.replace(/<[^>]+>/g, ''); // 匹配h标签的文字
        let itemLabel = item.match(/<\w+?>/)[0]; // 匹配h?标签<h?>
        let levelIndex = levelStack.indexOf(itemLabel); // 判断数组里有无<h?>
        // 没有找到相应<h?>标签，则将新增ul、li
        if (levelIndex === -1) {
            levelStack.unshift(itemLabel);
            addStartUL();
            addLI(index, itemText);
        }
        // 找到了相应<h?>标签，并且在栈顶的位置则直接将li放在此ul下
        else if (levelIndex === 0) {
            addLI(index, itemText);
        }
        // 找到了相应<h?>标签，但是不在栈顶位置，需要将之前的所有<h?>出栈并且打上闭合标签，最后新增li
        else {
            while (levelIndex--) {
                levelStack.shift();
                addEndUL();
            }
            addLI(index, itemText);
        }
    });
    // 如果栈中还有<h?>，全部出栈打上闭合标签
    while (levelStack.length) {
        levelStack.shift();
        addEndUL();
    }
    return result;
}
