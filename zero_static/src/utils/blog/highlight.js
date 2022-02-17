/**
 * highlight.js  代码高亮,带行数
 * 在 main 中引入即可
 * author zwb
 */
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';
let Highlight = function(Vue) {
    Vue.directive('highlight', function(el) {
        // querySelectorAll可以根据自己的富文本生成的代码做调整 我这里默认刚好
        let blocks = el.querySelectorAll('pre code');
        blocks.forEach(block => {
            hljs.highlightBlock(block);
        });
    });
};
export default Highlight;
