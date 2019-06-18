using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour
{
    public float BulletMoveSpeed = 0.5f;
    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {
        BulletMove();
    }
    private void BulletMove()
    {
        transform.Translate(0, 0, BulletMoveSpeed * Time.deltaTime);
    }
    private void OnTriggerEnter(Collider other)
    {
        if (other.tag == "DestroyBounder")
        {
            Destroy(gameObject);
        }
        /*if (other.tag == "Enemy")//碰到敌人
        {
            Destroy(other.gameObject);//销毁敌人
            GameObject obj = Instantiate(PlayerExplosion, transform.position, Quaternion.identity);//生成爆炸物
            Destroy(obj, 0.3f);//销毁爆炸物
            Destroy(gameObject);//销毁自己
        }
        */
    }
  
}
