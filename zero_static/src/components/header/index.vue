<template>
    <div id="zero-header" class="zero-header">
        <div style="margin:0;padding:0;" class="zero-head-inner">
            <div class="zero-logo">
                <a href="/">
                    <i class="logo-img">
                        <span class="svg-container">
                            <svg-icon icon-class="sun" />
                        </span>
                    </i>
                    墨阳空间
                </a>
            </div>
            <div class="head-middle"></div>
            <div class="head-end">
                <a href="/help">
                    <i class="el-icon-question" title="帮助与文档" />
                    <span title=" 帮助与文档">
                        帮助与文档
                    </span>
                </a>
            </div>
        </div>
    </div>
</template>

<script>
export default {}
</script>

<style>
.zero-header {
    height: 60px;
    background-color: #2b303b;
    padding: 0 20px;
    min-width: 980px;
}
.zero-head-inner {
    display: flex;
    height: 60px;
    width: 100%;
}
.zero-logo {
    width: 160px;
    height: 60px;
    padding: 18px;
    color: antiquewhite;
    font-size: 20px;
}

.logo-img {
    width: 60px;
    height: 60px;
    background: require('@/assets/logo.png');
}

.head-middle {
    width: 85%;
}
.head-end {
    width: 160px;
    height: 60px;
    padding: 20px;
    color: antiquewhite;
    font-size: 16px;
}
.head-end span:hover {
    color: rgb(26, 111, 160);
}
</style>
