using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public enum EnemyType//敌人的类型
{
    Simple,
    Middle,
    Higher,
}
public class Enemy : MonoBehaviour
{

    public EnemyType enemyType;//类型
    public float MoveSpeed;//移动速度
    public int score = 0;
    public GameObject ExplosionVFX;//陨石爆炸粒子
    private AudioSource ExplosionAS;
    void Start()
    {
        ExplosionAS = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        DealWithEnemyType();
    }
    public void DealWithEnemyType()//处理敌人类型的各种操作，敌人类型不同，攻击运动方式也不一样
    {
        switch (enemyType)
        {
            case EnemyType.Simple:
                SimpleEnemyMoveMent();
                break;
            case EnemyType.Middle:
                break;
            case EnemyType.Higher:
                break;
            default:
                break;
        }
    }
    private void SimpleEnemyMoveMent()
    {
        transform.Translate(Vector3.forward * MoveSpeed * Time.deltaTime);
        if (transform.position.z < -30)
        {
            Destroy(gameObject);//销毁本体
        }
    }
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Bullet")//与子弹进行碰撞
        {
           
            Destroy(other.gameObject);//销毁子弹
            GameObject ex = Instantiate(ExplosionVFX, transform.position, Quaternion.identity);//生成爆炸粒子
            M_GameManager._instance.AddScore(++score);
            Destroy(ex, 0.3f);//销毁爆炸粒子
            Destroy(gameObject);//销毁本体
            
        }
    }

}

