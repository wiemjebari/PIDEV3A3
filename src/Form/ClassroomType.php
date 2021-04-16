<?php

namespace App\Form;

use App\Entity\Classroom;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ClassroomType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('Name', TextType::class,[
                'label'=>'Nom Classroom',
                'attr'=>[
                    'placeholder'=>'Merci de définir le nom',
                    'class'=>'name'
                ]
            ])//Name<input type=texte>
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Classroom::class,
        ]);
    }
}
