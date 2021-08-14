<template>
    <div class="account-register-card">
        <div class="account-register-title">
            <i class="account-img svg-item">
                <span class="svg-container">
                    <svg-icon icon-class="account-blue" />
                </span>
            </i>
            <span style="vertical-align: middle;margin-left: 10px;">
                墨阳账号注册
            </span>
        </div>
        <div class="account-register-block">
            <div class="layout-block">
                <div class="account-register-form">
                    <Form
                        ref="formRegister"
                        :model="accountInfo"
                        :rules="ruleCustom"
                    >
                        <FormItem prop="account">
                            <Input
                                v-model="accountInfo.account"
                                placeholder="墨阳账号emy：6-32个字符"
                                type="text"
                            />
                        </FormItem>
                        <FormItem prop="password">
                            <Input
                                v-model="accountInfo.password"
                                placeholder="密码：6-32位的数字、字母与特殊符号(除空格外)两种及以上的组合"
                                type="password"
                            />
                        </FormItem>
                        <FormItem prop="passwordCheck" style="margin-bottom: 0">
                            <Input
                                v-model="accountInfo.passwordCheck"
                                placeholder="确认密码"
                                type="password"
                            />
                        </FormItem>
                        <el-divider style="color: #99a9bf">手机信息</el-divider>
                        <FormItem prop="phone">
                            <Input
                                v-model="accountInfo.phone"
                                placeholder="手机号"
                                type="text"
                            />
                        </FormItem>
                        <FormItem prop="smsCode" style="margin-bottom: 10px;">
                            <Input
                                v-model="accountInfo.smsCode"
                                number
                                placeholder="验证码"
                                style="width: 45%"
                                type="text"
                            />
                            <Button
                                class="i-button-checked"
                                @click="getSmsCode(accountInfo.phone)"
                            >
                                {{
                                    verifyReShow
                                        ? '重新获取验证码'
                                        : verifyShow
                                        ? timeout + 's'
                                        : '获取验证码'
                                }}
                            </Button>
                        </FormItem>
                        <FormItem style="margin-bottom: 25px;text-align: left;">
                            <Checkbox v-model="checked">
                                我已阅读并同意
                            </Checkbox>
                            墨阳空间
                            <router-link
                                class="see-page-agreement"
                                title="查看墨阳空间服务协议"
                                to="/help/page/service"
                            >
                                服务协议
                            </router-link>
                            和
                            <router-link
                                class="see-page-agreement"
                                title="查看墨阳空间隐私声明"
                                to="/help/page/privacy"
                            >
                                隐私声明
                            </router-link>
                        </FormItem>
                        <FormItem>
                            <Button
                                style="height: 40px;width: 100%"
                                type="primary"
                                @click="handleSubmit('formRegister')"
                            >
                                同意协议并提交
                            </Button>
                        </FormItem>
                    </Form>
                </div>
            </div>
        </div>
        <div class="block-bottom">
            <div class="other-register-way">
                <span class="other-register-way-text">
                    其他登录方式>>
                </span>
                <span>
                    <i class="svg-item">
                        <span class="svg-container">
                            <svg-icon
                                class="svg-item-li"
                                icon-class="email-blue"
                                @click="toOtherRegisterWay('/register/email')"
                            />

                            <svg-icon
                                class="svg-item-li"
                                icon-class="QQ-color"
                                @click="toOtherRegisterWay('/register/qq')"
                            />

                            <svg-icon
                                class="svg-item-li"
                                icon-class="weixin"
                                @click="toOtherRegisterWay('/register/wx')"
                            />
                        </span>
                    </i>
                </span>
            </div>
        </div>
    </div>
</template>

<script>
import {
    checkSpaceExist,
    delSpace,
    validPasswordFormat,
    validPhoneFormat
} from '@/utils/validate.js'
import {getCheckCode} from '@/api/register'

export default {
    name: 'AccountRegister',
    data() {
        const validateAccount = (rule, value, callback) => {
            value = value.trim()
            this.accountInfo.account = delSpace(this.accountInfo.account)
            if (value === '') {
                callback(new Error('请输入墨阳账号'))
            } else if (value.length < 6 || value.length > 32) {
                callback(new Error('要求长度6-32个字符'))
            } else {
                if (this.accountExist) {
                    callback(new Error('该账号已经存在'))
                }
                callback()
            }
        }
        const validatePassword = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'))
            } else if (checkSpaceExist(value)) {
                callback(new Error('密码中不能包含空格'))
            } else if (value.length < 6 || value.length > 32) {
                callback(new Error('要求长度6-32个字符'))
            } else if (!validPasswordFormat(value)) {
                callback(
                    new Error(
                        '必须是数字、字母与特殊符号(除空格外)两种及以上的组合'
                    )
                )
            } else {
                if (this.accountInfo.passwordCheck !== '') {
                    // 对第二个密码框单独验证
                    this.$refs.formRegister.validateField('passwordCheck')
                }
                callback()
            }
        }
        const validatePasswordCheck = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请确认密码'))
            } else if (value !== this.accountInfo.password) {
                callback(new Error('两次输入的密码不匹配'))
            } else {
                callback()
            }
        }
        const validatePhone = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入手机号'))
            } else if (!validPhoneFormat(value)) {
                callback(new Error('手机号格式不正确'))
            } else {
                callback()
            }
        }
        return {
            accountInfo: {
                account: '',
                password: '',
                passwordCheck: '',
                phone: '',
                smsCode: ''
            },
            accountExist: false,
            checked: false,
            verifyShow: false,
            verifyReShow: false,
            timeout: 60,
            ruleCustom: {
                account: [
                    {
                        validator: validateAccount,
                        trigger: 'blur'
                    }
                ],
                password: [
                    {
                        validator: validatePassword,
                        trigger: 'blur'
                    }
                ],
                passwordCheck: [
                    {
                        validator: validatePasswordCheck,
                        trigger: 'blur'
                    }
                ],
                phone: [
                    {
                        validator: validatePhone,
                        trigger: 'blur'
                    }
                ]
            }
        }
    },
    methods: {
        toOtherRegisterWay(path) {
            if (path) {
                this.$router.push(path)
            } else {
                this.$message.error('跳转页面失败！')
            }
        },
        getSmsCode(phone) {
            const param = {
                phone: phone
            }
            getCheckCode(param)
            this.verifyReShow = false
            if (!this.verifyShow) {
                // to do
                const interval = setInterval(() => {
                    this.timeout = this.timeout - 1
                    if (this.timeout === 0) {
                        clearInterval(interval)
                        this.verifyReShow = true
                        this.verifyShow = false
                        this.timeout = 60
                    }
                }, 1000)
            }
            this.verifyShow = true
        }
    }
}
</script>

<style scoped>
.account-register-card {
    height: 620px;
}

.account-register-title {
    height: 72px;
    border-bottom: #e5e5e5 1px solid;
    position: relative;
    background-color: #fff;
    font-size: 18px;
    padding: 24px;
}

.account-img {
    vertical-align: middle;
}

.svg-item {
    display: inline-block;
    line-height: 20px;
    font-size: 32px;
    transition: all 0.4s ease-in-out;
}

.svg-item-li {
    margin-left: 12px;
    margin-bottom: -5px;
    cursor: pointer;
}

.account-register-block {
    background-color: #fff;
    height: 500px;
}

.layout-block {
    width: 100%;
    margin: auto;
    height: 500px;
}

.account-register-form {
    width: 45%;
    margin: auto;
    padding: 30px 30px 0 30px;
}

.block-bottom {
    padding-right: 10px;
    padding-top: 10px;
    height: 48px;
}

.other-register-way {
    float: right;
}

.i-button-checked {
    height: 45px;
    width: 45%;
    margin-left: 10%;
    color: #20a0ff;
}

.see-page-agreement {
    color: #20a0ff;
}

.see-page-agreement:hover {
    text-decoration: underline;
}
</style>
