<template>
    <div class="header-search-tool">
        <el-input
            placeholder="请输入内容"
            v-model="input"
            :class="inputWidth ? 'searchWidget-520' : 'searchWidget-240'"
            @focus="searchFocus"
            @blur="searchBlur"
            @keydown.enter.native.prevent="searchForInput"
        >
            <i slot="suffix" class="el-input__icon el-icon-search"></i>
        </el-input>
    </div>
</template>

<script>
export default {
    data() {
        return {
            input: '',
            inputWidth: false
        };
    },
    methods: {
        searchForInput() {
            console.log(this.input);
        },
        searchFocus() {
            this.inputWidth = true;
        },
        searchBlur() {
            this.inputWidth = false;
        }
    }
};
</script>
<style lang="scss">
.header-search-tool {
    text-align: end;
    margin-right: 40px;
}
.searchWidget-240 {
    width: 240px;
}
.searchWidget-520 {
    width: 520px;
}
.header-search-tool .el-input .el-input__inner {
    padding-right: 30px;
    border-radius: 20px;
}
.header-search-tool .el-input__inner {
    height: 36px;
    line-height: 36px;
}
.header-search-tool .el-input.is-active .el-input__inner,
.el-input__inner:focus {
    border-color: #e6a23c;
    outline: 0;
}
</style>
