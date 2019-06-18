using System.Collections;
using System.Collections.Generic;
using UnityEngine;
[System.Serializable]
public class PlayerClampBounder//移动的X，Z最大范围或者最小范围类
{
    public float MaxX;
    public float MaxZ;
    public float MinX;
    public float MinZ;
}
public class Player : MonoBehaviour
{
    public PlayerClampBounder Bounder;//边界范围
    public float MoveSpeed;//移动速度

    public float ShotDuratime = 0.3f;//发射的间隔时间
    public GameObject BulletPrefab;//子弹的预制体，在unity进行赋值
    public GameObject PlayerExplosion;
    private Transform BulletPoint;//子弹生成的位置
    // Start is called before the first frame update
    void Start()
    {
        BulletPoint = transform.Find("BulletPoint");
    }

    // Update is called once per frame
    void Update()
    {
        MoveMent();
        ShotBullet();
    }
    public void MoveMent()//控制移动的方法
    {

        float Horizontal = Input.GetAxis("Horizontal");//获取水平输入轴
        float Vertical = Input.GetAxis("Vertical");//获取垂直输入轴
        if (transform.position.x >= Bounder.MaxX && Horizontal > 0)//如果当前x位置大于等于边界范围，且还在向这个方向运动，也就是说还按下了向右，就让水平输入值为0，也就不会再继续向右
        {
            Horizontal = 0;
        }
        else if (transform.position.x <= Bounder.MinX && Horizontal < 0)//同理
        {
            Horizontal = 0;
        }
        if (transform.position.z >= Bounder.MaxZ && Vertical > 0)
        {
            Vertical = 0;
        }
        else if (transform.position.z <= Bounder.MinZ && Vertical < 0)
        {
            Vertical = 0;
        }
        transform.Translate(Horizontal * MoveSpeed * Time.deltaTime, 0, Vertical * Time.deltaTime * MoveSpeed);//移动
    }
    private void ShotBullet()
    {
        ShotDuratime -= Time.deltaTime;
        if (ShotDuratime <= 0 && Input.GetMouseButton(0))//当时间到了且按下了鼠标左键
        {
            BulletPoint = transform.Find("BulletPoint");
            Instantiate(BulletPrefab, BulletPoint.position, Quaternion.identity);//生成子弹
            ShotDuratime = 0.3f;//重新设置间隔时间

        }

    }
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Enemy")//碰到敌人
        {
            Destroy(other.gameObject);//销毁敌人
            GameObject obj = Instantiate(PlayerExplosion, transform.position, Quaternion.identity);//生成爆炸物
            Destroy(obj, 0.3f);//销毁爆炸物
            Destroy(gameObject);//销毁自己
        }
    }
}
