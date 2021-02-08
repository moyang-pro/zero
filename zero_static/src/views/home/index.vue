<template>
    <div class="home">
        <ZeroHeader />
    </div>
</template>

<script>
// @ is an alias to /src
import ZeroHeader from '@/components/header/index.vue'

export default {
    name: 'Home',
    components: {
        ZeroHeader
    }
}
</script>
