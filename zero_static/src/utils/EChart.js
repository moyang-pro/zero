class EChart {
    defaultConfig = null;
    // 默认色板
    colors = [
        '#ff7f50',
        '#87cefa',
        '#da70d6',
        '#32cd32',
        '#6495ed',
        '#ff69b4',
        '#ba55d3',
        '#cd5c5c',
        '#ffa500',
        '#40e0d0',
        '#1e90ff',
        '#ff6347',
        '#7b68ee',
        '#00fa9a',
        '#ffd700',
        '#6699FF',
        '#ff6666',
        '#3cb371',
        '#b8860b',
        '#30e0e0'
    ];

    //生成从minNum到maxNum的随机数
    randomNum(minNum, maxNum) {
        switch (arguments.length) {
            case 1:
                return parseInt(Math.random() * minNum + 1, 10);
            case 2:
                return parseInt(Math.random() * (maxNum - minNum + 1) + minNum, 10);
            default:
                return 0;
        }
    }

    getColorsByLength(len) {
        if (len > 20) {
            console.log('太长了');
        }
        let startIndex = Math.abs(this.randomNum(0, 20) - 20);
        return this.colors.slice(startIndex, startIndex + len);
    }

    showLineChart(myChart, config, data) {
        if (config) {
            this.defaultConfig = config;
        }

        if (!data || data.length === 0) {
            return;
        }
        const len = data.length;
        let dataColors = this.getColorsByLength(len);
        if (dataColors.length !== len) {
            return;
        }
        // 数据处理
        let dataX = [];
        let dataY = [];
        data.forEach((item, index) => {
            dataX.push(item.label);
            dataY.push({
                value: item.count,
                itemStyle: {
                    color: dataColors[index]
                }
            });
        });
        let option = {
            title: {
                text: '文章标签统计'
            },
            xAxis: {
                type: 'category',
                data: dataX
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: dataY,
                    label: {
                        show: true
                    },
                    type: 'bar'
                }
            ]
        };
        // 绘制图表
        myChart.setOption(option);
    }
}
export default new EChart();
