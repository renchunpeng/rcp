import request from '../utils/request';


var getPercent = function (v1, v2) {
  return (Number.parseFloat(v1) / Number.parseFloat(v2) * 100).toFixed(2);
};
var getNameDesc = function (ar) {
  var res = [ ];
  ar.sort(function (a, b) {
    return getPercent(a.value[2], a.value[3]) - getPercent(b.value[2], b.value[3]);
  });
  for (var i = 0; i < ar.length; i ++) {
    res.push(ar[i].name);
  }
  return res;
};
var getValueIDesc = function (ar, func) {
  var res = [ ];
  ar.sort(function (a, b) {
    return getPercent(a.value[2], a.value[3]) - getPercent(b.value[2], b.value[3]);
  });
  for (var i = 0; i < ar.length; i ++) {
    res.push(func(ar[i].value));
  }
  return res;
};
var getSortAndSplit = function (ar) {
  var res = ar.sort(function (a, b) {
    return a.value - b.value;
  });
  var names = [], values = [];
  for (var i = 0; i < res.length; i ++) {
    names.push(res[i].name);
    values.push(res[i].value);
  }
  return {
    names: names,
    values: values
  }
};


var eo = {
  '/statistic/dashboard': {
    title: de.dashboard.title,
    data: de.dashboard.text
  },
  '/statistic/spread': {
    title: de.spread.title,
    data: de.spread.text,
    options: {
      backgroundColor: '#404a59',
      tooltip : {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        y: 'bottom',
        x:'right',
        data:['服务社总数'],
        textStyle: {
          color: '#fff'
        }
      },
      geo: {
        map: '贵州',
        left: 60,
        roam: true,
        itemStyle: {
          normal: {
            areaColor: '#323c48',
            borderColor: '#111'
          },
          emphasis: {
            areaColor: '#2a333d',
            show: false
          }
        }
      },
      brush: {
        outOfBrush: {
          color: '#abc'
        },
        brushStyle: {
          borderWidth: 2,
          color: 'rgba(0,0,0,0.2)',
          borderColor: 'rgba(0,0,0,0.5)',
        },
        seriesIndex: [0, 1],
        throttleType: 'debounce',
        throttleDelay: 300,
        geoIndex: 0
      },
      grid: {
        right: 40,
        top: 100,
        bottom: 40,
        width: '30%'
      },
      xAxis: {
        type: 'value',
        scale: true,
        position: 'top',
        boundaryGap: false,
        splitLine: {show: false},
        axisLine: {show: false},
        axisTick: {show: false},
        axisLabel: {margin: 2, textStyle: {color: '#aaa'}, show: true}
      },
      yAxis: {
        type: 'category',
        name: de.spread.info,
        nameGap: 16,
        axisLine: {show: false, lineStyle: {color: '#ddd'}},
        axisTick: {show: false, lineStyle: {color: '#ddd'}},
        data: getNameDesc(de.spread.data)
      },
      series : [
        {
          name: '服务社总数',
          type: 'effectScatter',
          coordinateSystem: 'geo',
          symbolSize: function (val) {
            return val[2] / 60;
          },
          showEffectOn: 'render',
          rippleEffect: {
            brushType: 'stroke'
          },
          hoverAnimation: true,
          label: {
            normal: {
              formatter: function (params) {
                var v = params.value.toString().split(','),
                  r = getPercent(v[2], v[3]);
                return params.name + '\n' +
                  '服务社' + v[2] + '个/' +
                  '行政村' + v[3] + '个\n' +
                  '渗透率' + r + '%';
              },
              position: 'bottom',
              show: true
            }
          },
          itemStyle: {
            normal: {
              color: '#f4e925',
              shadowBlur: 10,
              shadowColor: '#333'
            }
          },
          zlevel: 1,
          data: de.spread.data
        },
        {
          id: 'bar',
          zlevel: 2,
          type: 'bar',
          symbol: 'none',
          itemStyle: {
            normal: {
              color: '#ddb926'
            }
          },
          label: {
            normal: {
              show: true,
              position: 'right',
              formatter: '{c}%'
            }
          },
          data: getValueIDesc(de.spread.data, function (v) {
            return getPercent(v[2], v[3]);
          })
        }
      ]
    }
  },
  '/statistic/farmers': {
    title: de.farmers.title,
    data: de.farmers.text,
    options: {
      grid: [
        {x: '7%', y: '7%', width: '85%', height: '38%'},
        {x: '7%', y2: '7%', width: '85%', height: '38%'}
      ],
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: [ de.farmers.data.tMonth, de.farmers.data.tYear ]
      },
      xAxis: [
        {
          gridIndex: 0,
          type: 'category',
          boundaryGap: false,
          data: de.farmers.data.xMonth
        },
        {
          gridIndex: 1,
          type: 'category',
          boundaryGap: false,
          data: de.farmers.data.xYear
        }
      ],
      yAxis: [
        {
          gridIndex: 0,
          type: 'value'
        },
        {
          gridIndex: 1,
          type: 'value'
        }
      ],
      series: [
        {
          name:de.farmers.data.tMonth,
          type:'line',
          label: {
            normal: {
              show: true,
              position: 'top',
              formatter: function (params) {
                return params.value || '';
              },
              textStyle: {
                fontSize: 18,
                fontWeight: 'bolder',
                color: '#222'
              }
            }
          },
          xAxisIndex: 0,
          yAxisIndex: 0,
          stack: '总量',
          data:de.farmers.data.dataMonth
        },
        {
          name:de.farmers.data.tYear,
          type:'line',
          label: {
            normal: {
              show: true,
              position: 'top',
              formatter: function (params) {
                return params.value || '';
              },
              textStyle: {
                fontSize: 18,
                fontWeight: 'bolder',
                color: '#222'
              }
            }
          },
          xAxisIndex: 1,
          yAxisIndex: 1,
          stack: '总量',
          data:de.farmers.data.dataYear
        }
      ]
    }
  },
  '/statistic/supply': {
    title: de.supply.title,
    data: de.supply.text,
    options: {
      series: [
        {
          name:'面积模式',
          type:'pie',
          radius: ['50%', '70%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: true,
              textStyle: {
                fontSize: 16
              },
              position: 'outside',
              formatter: '{b}:{c}项({d}%)'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: 22,
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: true
            }
          },
          data: de.supply.data
        }
      ]
    }
  },
  '/statistic/sale': {
    title: de.sale.title,
    data: de.sale.text,
    options: {
      legend: {
        data: de.sale.data.years
      },
      toolbox: {
        show : true,
        feature : {
          dataView : {show: true, readOnly: false},
          magicType : {show: true, type: ['line', 'bar']},
          restore : {show: true},
          saveAsImage : {show: true}
        }
      },
      calculable : true,
      xAxis : [
        {
          type : 'category',
          data : de.sale.data.months
        }
      ],
      yAxis : [
        {
          type : 'value'
        }
      ],
      series : [
        {
          name: de.sale.data.years[0],
          type:'bar',
          data:de.sale.data.sYear,
          label: {
            normal: {
              show: true,
              position: 'top',
              formatter: '￥{c}'
            },
            emphasis: {
              textStyle: {
                fontSize: 20,
                fontWeight: 'bolder',
                color: '#232433'
              }
            }
          },
          markLine : {
            label: {
              normal: {
                show: true,
                position: 'end',
                formatter: '平均 ￥{c}'
              }
            },
            data : [
              {type : 'average', name: '平均值'}
            ]
          }
        },
        {
          name: de.sale.data.years[1],
          type:'bar',
          itemStyle: {
            normal: {
              color: '#999'
            }
          },
          data:de.sale.data.sLastYear,
          label: {
            normal: {
              show: true,
              position: 'top',
              formatter: '￥{c}'
            },
            emphasis: {
              textStyle: {
                fontSize: 20,
                fontWeight: 'bolder',
                color: '#232433'
              }
            }
          },
          markLine : {
            label: {
              normal: {
                show: true,
                position: 'end',
                formatter: '平均 ￥{c}'
              }
            },
            data : [
              {type : 'average', name : '平均值'}
            ]
          }
        }
      ]
    }
  },
  '/statistic/county': {
    title: de.county.title,
    options: {
      legend: {
        data: [de.county.info]
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
      },
      yAxis: {
        type: 'category',
        data: getSortAndSplit(de.county.data).names
      },
      series: [
        {
          name: de.county.info,
          type: 'bar',
          label: {
            normal: {
              show: true,
              position: 'right',
              formatter: '￥ {c}'
            }
          },
          data: getSortAndSplit(de.county.data).values
        }
      ]
    }
  },
  '/statistic/point': {
    title: de.point.title,
    options: {
      legend: {
        data: [de.point.info]
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01]
      },
      yAxis: {
        type: 'category',
        data: getSortAndSplit(de.point.data).names
      },
      series: [
        {
          name: de.point.info,
          type: 'bar',
          label: {
            normal: {
              show: true,
              position: 'right',
              formatter: '￥ {c}'
            }
          },
          itemStyle: {
            normal: {
              color: '#386499'
            }
          },
          data: getSortAndSplit(de.point.data).values
        }
      ]
    }
  },
  '/statistic/requirement': {
    title: de.requirement.title,
    data: de.requirement.text,
    options: {
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        x: 'left',
        data: de.requirement.data.series
      },
      radar: {
        indicator: function (c, t) {
          var res = [];
          for (var i = 0; i < c.length; i ++) {
            res.push({ text: c[i], max: t[i] });
          }
          return res;
        }(de.requirement.data.categories, de.requirement.data.rTotal),
        nameGap: 20,
        axisLabel: {
          show: true,
          margin: 20,
          textStyle: {
            fontSize: 20,
            color: '#999'
          },
          formatter: function (value, index) {
            if (index == 5) {
              return value;
            }
            else {
              return ''
            }
          }
        }
      },
      series: [
        {
          type: 'radar',
          itemStyle: {
            normal: {
              areaStyle: {
                type: 'default'
              },
              color: '#99accc'
            }
          },
          label: {
            normal: {
              show: true,
              position: 'inside',
              textStyle: {
                fontSize: 18,
                fontWeight: 'bolder',
                color: '#333'
              }
            }
          },
          data: [
            {
              name: de.requirement.data.series[1],
              value:de.requirement.data.rFill
            }
          ]
        }
      ]
    }
  }
};

export default eo;


