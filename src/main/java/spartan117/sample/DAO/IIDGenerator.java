/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spartan117.sample.DAO;

/**
 *
 * @author turkeylock
 */

//id生成算法接口
public interface IIDGenerator {
    String getID(String seed);
}
