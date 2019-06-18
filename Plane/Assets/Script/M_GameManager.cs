using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class M_GameManager : MonoBehaviour
{

    public Transform EnemySpawnPosition;//生成点的基础位置
    private float SpawnDurationTime = 2;//生成的间隔时间
    public GameObject Enemy01;//生成的敌人预制体

    public int Score;//得分
    public static M_GameManager _instance;//单例
    public Text ScoreText;//显示分数的文本组件
    private void Awake()
    {
        _instance = this;//获得单例
    }
    void Start()
    {

    }


    void Update()
    {
        CreatEnemy();
    }
    private void CreatEnemy()//生成的方法
    {
        SpawnDurationTime -= Time.deltaTime;
        if (SpawnDurationTime <= 0)
        {
            Instantiate(Enemy01, new Vector3(GetRandomPos(-4, 4), EnemySpawnPosition.position.y, EnemySpawnPosition.position.z), EnemySpawnPosition.rotation);
            SpawnDurationTime = 2.0f;
        }

    }
    private float GetRandomPos(float Min, float Max)//生成随机数的方法
    {
        return Random.Range(Min, Max);
    }
    public void AddScore(int amount)//更新分数的方法
    {
        if (amount % 2 == 1)
        {
            Score += amount;
            ScoreText.text = "得分：" + Score;
        }
    }
}
