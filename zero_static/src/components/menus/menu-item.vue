<template>
    <div v-if="!item.hidden" class="menu-item-div">
        <template v-if="!hasShowingChild(item) && item.leaf">
            <zero-link :to="item.path">
                <!-- 情况二：没子集的情况-->
                <el-menu-item :key="item.code" :index="item.code">
                    <i :class="item.iconClass" v-if="item.iconClass"></i>
                    <span slot="title">{{ item.name }}</span>
                </el-menu-item>
            </zero-link>
        </template>
        <!--情况一：有子集的情况 -->
        <el-submenu :key="item.code" :index="item.code" v-else>
            <template slot="title">
                <i :class="item.iconClass" v-if="item.iconClass"></i>
                <span>{{ item.name }}</span>
            </template>
            <menu-item v-for="child in item.children" :key="child.code" :item="child" />
        </el-submenu>
    </div>
</template>

<script>
import zeroLink from '@/components/menus/zero-link';
export default {
    name: 'menu-item',
    components: {
        zeroLink
    },
    props: {
        item: {
            type: Object,
            required: true
        }
    },
    created() {
        console.log(this.item);
    },
    methods: {
        hasShowingChild(parent) {
            if (!parent || !parent.children) {
                return false;
            }
            let children = parent.children;
            const showingChildren = children.filter(item => {
                return !item.hidden;
            });
            if (showingChildren.length >= 1) {
                return true;
            }
            if (showingChildren.length === 0) {
                return false;
            }

            return false;
        }
    }
};
</script>

<style scoped lang="scss">
.menu-item-div {
}
</style>
