<template>
    <div id="zero-footer" class="zero-footer">
        <div class="zero-footer-inner">
            <span>
                Copyright © 2020-2022 Moyang Room. All Rights Reserved. 零度墨阳 版权所有
            </span>
        </div>
    </div>
</template>
<script>
export default {
    name: 'Footer'
};
</script>
<style>
.zero-footer {
    height: 60px;
    line-height: 60px;
    text-align: center;
    left: 0;
    width: 100%;
    background-color: #f7f8fa;
    border-top: #dedfe1 1px solid;
    /*min-width: 980px;*/
}
.zero-footer-inner {
    color: #97a8be;
}
@media screen and (max-width: 600px) {
    .zero-footer {
        font-size: 12px;
    }
}
</style>
