<template>
    <Layout class="root-container">
        <Header class="zero-header-container">
            <ZeroHeader />
        </Header>
        <Content class="zero-main-container">
            <router-view />
        </Content>
        <Footer class="zero-footer-container">
            <ZeroFooter />
        </Footer>
    </Layout>
</template>

<script>
// @ is an alias to /src
import ZeroHeader from '@/components/header/index.vue';
import ZeroFooter from '@/components/footer/index';

export default {
    name: 'index',
    components: {
        ZeroHeader,
        ZeroFooter
    }
};
</script>

<style scoped></style>
